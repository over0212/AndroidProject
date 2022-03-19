package com.project.housing;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // 메서드에서 오늘 or 내일 날짜를 설정하기 위한 flag 값
    private final int dateFlag = 0;

    private String startMonth;
    private String endMonth;
    private String sidoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        addEventListener();
    }

    private void initData() {
        binding.startDate.setText(getTime(0));
        binding.endDate.setText(getTime(1));
        service = HousingService.retrofit.create(HousingService.class);
    }

    private void addEventListener() {
        // 공고 시작 날짜
        binding.startDate.setOnClickListener(view -> {
            callDatePickerDialog(binding.startDate, "start");
        });
        // 공고 마감 날짜
        binding.endDate.setOnClickListener(view -> {
            callDatePickerDialog(binding.endDate, "end");
        });

        binding.sidoBtn.setOnClickListener(view -> {
            addBottomSheetDialog();
        });

        binding.searchBtn.setOnClickListener(view -> {

            service.getHousingList(HousingService.decodingKey, startMonth, endMonth, sidoName).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if (response.isSuccessful()){
                        Log.d("TAG", response.body().getBody().getItems().getItem().get(0).toString());
                        Intent intent = new Intent(getApplicationContext(), HousingListActivity.class);
                        Items items = response.body().getBody().getItems();
//                        ArrayList<Item> itemArrayList = (ArrayList<Item>) items.getItem();
                        intent.putExtra("serialHousingListObj", items);
                        intent.putExtra("serialReqObj", new ReqHousingList(startMonth, endMonth, sidoName));
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Log.d("TAG", t.getMessage());
                }
            });

        });
    }
    
    private void addBottomSheetDialog() {
        sidoBottomSheetFragment = SidoBottomSheetFragment.getInstance(this);
        sidoBottomSheetFragment.show(getSupportFragmentManager(), SidoBottomSheetFragment.class.getName());
    }

    @Override
    public void onItemClick(String sidoName) {
        binding.sidoBtn.setText(sidoName);
        this.sidoName = sidoName;
        sidoBottomSheetFragment.dismiss();
    }

    private void callDatePickerDialog(Button button, String Type) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                button.setText(String.format("%d-%02d-%02d", i, i1 + 1, i2));
                Log.d(TAG, String.format("%d%02d", i, i1 + 1));
                if(Type.equals("start")) {
                    startMonth = String.format("%d%02d", i, i1 + 1);
                }else{
                    endMonth = String.format("%d%02d", i, i1 + 1);
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private String getTime(int flag) {
        // 오늘 날짜
        // 처음 button 에 입력할 변수
        String date;
        if (flag == 0) {
            date = dateFormat.format(calendar.getTime());
            return date;
            // 내일 날짜
        } else {
            calendar.add(calendar.DATE, +1);
            date = dateFormat.format(calendar.getTime());
            return date;
        }
    }
}
