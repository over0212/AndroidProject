package com.project.housing;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.project.housing.databinding.ActivityMainBinding;
import com.project.housing.interfaces.OnSidoItemClickListener;
import com.project.housing.models.request.ReqHousingList;
import com.project.housing.models.response.Item;
import com.project.housing.models.response.Items;
import com.project.housing.models.response.Response;
import com.project.housing.repository.HousingService;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements OnSidoItemClickListener {

    private static final String TAG = "TAG";
    private ActivityMainBinding binding;
    private SidoBottomSheetFragment sidoBottomSheetFragment;
    private HousingService service;

    // 날짜를 구하기 위해 Calendar 선언
    private final Calendar calendar = Calendar.getInstance();
    // 날짜를 원하는 pattern 으로 변경하기 위해 설정
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
    // 메서드에서 오늘 or 내일 날짜를 설정하기 위한 flag 값
    private final int dateFlag = 0;
    // main 에 바로 띄어주기 위해 선언
    private String startMonth;
    private String endMonth;
    private String allSidoName;
    private String formatStartMonth;
    private String formatEndMonth;
    private String selectedSidoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
        addEventListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "on start!");
        resetData();
        bindingViewAndData();
    }

    private void initData() {
        resetData();
        bindingViewAndData();
        // retrofit 초기화
        service = HousingService.retrofit.create(HousingService.class);
    }

    private void resetData(){
        startMonth = getTime(0);
        endMonth = getTime(1);
        allSidoName = "전국";
        formatStartMonth = startMonth;
        formatEndMonth = endMonth;
        selectedSidoName = allSidoName;
    }

    private void bindingViewAndData(){
        binding.startDate.setText(startMonth);
        binding.endDate.setText(endMonth);
        binding.sidoBtn.setText(allSidoName);
    }

    @SuppressLint("DefaultLocale")
    private void addEventListener() {
        // 공고 시작 날짜
        binding.startDate.setOnClickListener(view -> {
            callDatePickerDialog(binding.startDate, "start");
            Log.d(TAG, "공모 시작 날짜 버튼 : " + binding.startDate.getText());
        });
        // 공고 마감 날짜
        binding.endDate.setOnClickListener(view -> {
            callDatePickerDialog(binding.endDate, "end");
            Log.d(TAG, "공모 종료 날짜 버튼 : " + binding.endDate.getText());
        });
        // 공모 지역
        binding.sidoBtn.setOnClickListener(view -> {
            addBottomSheetDialog();
        });
        // 검색
        binding.searchBtn.setOnClickListener(view -> {
            if (selectedSidoName.equals(allSidoName)){
                selectedSidoName = null;
            }
            service.getHousingList(HousingService.decodingKey_J, startMonth, endMonth, selectedSidoName, 1).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getBody() != null && response.body().getBody().getTotalCount() > 0) {
                            Intent intent = new Intent(getApplicationContext(), HousingListActivity.class);
                            Items items = response.body().getBody().getItems();
                            int totalCount = response.body().getBody().getTotalCount();
                            int lastPageNum = calcLastPageNumByTotalCount(totalCount);
                            intent.putExtra("serialHousingListObj", items);
                            intent.putExtra("serialObj", new ReqHousingList(formatStartMonth, formatEndMonth));
                            intent.putExtra("serialParamObj", new ReqHousingList(startMonth, endMonth, selectedSidoName));
                            intent.putExtra("lastPageNum", lastPageNum);
                            startActivity(intent);
                        } else{
                            showAlertDialog();
                        }
                    }else{
                        showAlertDialog();
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Log.d(TAG, "mainActivity retrofit error : " + t.getMessage());
                    showAlertDialog();
                }
            });
        });
    }

    private int calcLastPageNumByTotalCount(int totalCnt){
        int result = totalCnt / 10;
        if (totalCnt > result * 10){
            result++;
        }
        return result;
    }

    // 검색을 하고 데이터가 null 일 때 alertDialog 를 띄운다.
    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("데이터 조회 실패")
                .setMessage("검색에 맞는 조건을 찾지 못했습니다.\n다시 검색을 해주세요.")
                .setPositiveButton("확인", (dialogInterface, i) -> {
                    builder.setView(binding.getRoot());
                });
        builder.show();
        binding.sidoBtn.setText(selectedSidoName);
    }

    // bottom sheet fragment 연결
    private void addBottomSheetDialog() {
        sidoBottomSheetFragment = SidoBottomSheetFragment.getInstance(this);
        sidoBottomSheetFragment.show(getSupportFragmentManager(), SidoBottomSheetFragment.class.getName());
    }

    // 지역버튼을 클릭했을 때 bottom sheet 가 위로 올라오고 지역을 선택했을 때 사라진다.
    // bottom sheet 에서 아래로 슬라이딩 했을 때 사라지는 기능도 구현
    @Override
    public void onItemClick(String sidoName) {
        binding.sidoBtn.setText(sidoName);
        this.selectedSidoName = sidoName;
        sidoBottomSheetFragment.dismiss();
    }

    // 공모시작일, 공모마감일 버튼을 클릭했을 때 보여주는 기능
    private void callDatePickerDialog(Button button, String Type) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String formattedDate = String.format("%d-%02d", i, i1 + 1, i2);
                button.setText(formattedDate);
                if (Type.equals("start")) {
                    Log.d(TAG, "123123123" + formattedDate);
                    formatStartMonth = String.format("%d%02d", i, i1 + 1);
                    startMonth = formattedDate;
                } else {
                    Log.d(TAG, "123123123" + formattedDate);
                    formatEndMonth = String.format("%d%02d", i, i1 + 1);
                    endMonth = formattedDate;
                }
                Log.d(TAG, "start Month : " + startMonth);
                Log.d(TAG, "end Month : " + endMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    // month 초기화 메서드
    private String getTime(int flag) {
        Calendar calendarForInit = Calendar.getInstance();
        if (flag == 0) {
            // 이전년도
            calendarForInit.add(calendar.YEAR, -1);
            calendarForInit.add(calendar.MONTH, +1);
        }
        return dateFormat.format(calendarForInit.getTime());
    }
}
