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
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat dateFormatForInitial = new SimpleDateFormat("yyyyMM");
    // main 에 바로 띄어주기 위해 선언
    private String startMonth;
    private String endMonth;
    private String allSidoName;
    private String paramStartMonth;
    private String paramEndMonth;
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
        startMonth = getTime(0); // 오늘 기준 작년 월로 설정
        endMonth = getTime(1); // 오늘 기준 금년 월로 설정
        allSidoName = "전국";
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
        });
        // 공고 마감 날짜
        binding.endDate.setOnClickListener(view -> {
            callDatePickerDialog(binding.endDate, "end");
        });
        // 공모 지역
        binding.sidoBtn.setOnClickListener(view -> {
            addBottomSheetDialog();
        });
        // 검색
        binding.searchBtn.setOnClickListener(view -> {
            if (selectedSidoName != null && selectedSidoName.equals(allSidoName)){
                selectedSidoName = null;
            }
            requestHousingData(new ReqHousingList(paramStartMonth, paramEndMonth, selectedSidoName));
        });
    }

    private void requestHousingData(ReqHousingList paramList){
       service.getHousingList(HousingService.decodingKey, paramList.getStartMonth(), paramList.getEndMonth(), paramList.getSidoName(), 1).enqueue(new Callback<Response>() {
           @Override
           public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
               Log.d(TAG, "param start month : " + paramStartMonth);
               Log.d(TAG, "param end month : " + paramEndMonth);
               Log.d(TAG, "selected sido : " + selectedSidoName);
               Log.d(TAG, "call : " + call.request().url());
               Log.d(TAG, "-------------------------------");
               if (response.isSuccessful()) {
                   if (response.body().getBody() != null && response.body().getBody().getTotalCount() > 0) {
                       Intent intent = new Intent(getApplicationContext(), HousingListActivity.class);
                       Items items = response.body().getBody().getItems();
                       int totalCount = response.body().getBody().getTotalCount();
                       int lastPageNum = calcLastPageNumByTotalCount(totalCount);
                       Log.d(TAG, "totalCount : " + totalCount);
                       Log.d(TAG, "lastPageNum : " + lastPageNum);
                       intent.putExtra("serialHousingListObj", items);
                       intent.putExtra("serialObj", new ReqHousingList(startMonth, endMonth));
                       intent.putExtra("serialParamObj", paramList);
                       intent.putExtra("lastPageNum", lastPageNum);
                       startActivity(intent);
                   } else{
                       assert response.body().getBody() != null;
                       Log.d(TAG, "response success but data count : " + response.body().getBody().getTotalCount());
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
    }

    private int calcLastPageNumByTotalCount(int totalCnt) {
        if (totalCnt < 10) {
            return 1;
        } else {
            int result = totalCnt / 10;
            if (totalCnt > result * 10) {
                result++;
            }
            return result;
        }
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
        if (selectedSidoName != null) {
            binding.sidoBtn.setText(selectedSidoName);
        }else{
            binding.sidoBtn.setText(allSidoName);
        }
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
                String dateFormatByHyphen = String.format("%d-%02d", i, i1 + 1, i2);
                button.setText(dateFormatByHyphen);
                if (Type.equals("start")) {
                    paramStartMonth = String.format("%d%02d", i, i1 + 1);
                    startMonth = dateFormatByHyphen;
                } else {
                    paramEndMonth = String.format("%d%02d", i, i1 + 1);
                    endMonth = dateFormatByHyphen;
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    // month 초기화 메서드
    private String getTime(int flag) {
        Calendar calendarForInit = Calendar.getInstance();
        if (flag == 0) {
            // startDate 초기값 : 작년
            calendarForInit.add(calendar.YEAR, -1);
            calendarForInit.add(calendar.MONTH, +1);
            paramStartMonth = dateFormatForInitial.format(calendarForInit.getTime());
        }else{
            paramEndMonth = dateFormatForInitial.format(calendarForInit.getTime());
        }
        return dateFormat.format(calendarForInit.getTime());
    }
}
