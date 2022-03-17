package com.project.housing;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.project.housing.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private ActivityMainBinding binding;

    // 날짜를 구하기 위해 Calendar 선언
    private final Calendar calendar = Calendar.getInstance();
    // 날짜를 원하는 pattern 으로 변경하기 위해 설정
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // 메서드에서 오늘 or 내일 날짜를 설정하기 위한 flag 값
    private final int dateFlag = 0;

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
    }

    private void addEventListener() {
        // 공고 시작 날짜
        binding.startDate.setOnClickListener(view -> {
            callDatePickerDialog(binding.startDate);
        });
        // 공고 마감 날짜
        binding.endDate.setOnClickListener(view -> {
            callDatePickerDialog(binding.endDate);
        });

        binding.sidoBtn.setOnClickListener(view -> {

        });

        binding.searchBtn.setOnClickListener(view -> {

        });
    }

    private void callDatePickerDialog(Button button) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                button.setText(String.format("%d-%02d-%02d", i, i1 + 1, i2));
                Log.d(TAG, String.format("%d-%02d-%02d", i, i1 + 1, i2));
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
