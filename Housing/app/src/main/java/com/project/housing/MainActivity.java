package com.project.housing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.project.housing.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        addEventListener();
    }

    private void initData() {

    }

    private void addEventListener() {
        // 공고 시작 날짜
        binding.startDate.setOnClickListener(view -> {
            callDatePickerDialog(view);
        });
        // 공고 마감 날짜
        binding.endDate.setOnClickListener(view -> {
            callDatePickerDialog(view);
        });

        binding.sidoBtn.setOnClickListener(view -> {

        });

        binding.searchBtn.setOnClickListener(view -> {

        });
    }

    // date picker 불러오기
    private void callDatePickerDialog(View view){
        DatePickerFragment pickerFragment = DatePickerFragment.getInstance();
        pickerFragment.show(getSupportFragmentManager(), pickerFragment.getTag());
    }

    public void processDatePickerResult(int year, int month, int day){
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);

    }
}