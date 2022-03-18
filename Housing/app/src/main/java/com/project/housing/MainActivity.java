package com.project.housing;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.project.housing.databinding.ActivityMainBinding;
import com.project.housing.interfaces.OnSidoItemClickListener;

public class MainActivity extends AppCompatActivity implements OnSidoItemClickListener {

    private ActivityMainBinding binding;
    private SidoBottomSheetFragment sidoBottomSheetFragment;

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
            addBottomSheetDialog();
        });

        binding.searchBtn.setOnClickListener(view -> {

        });
    }

    // date picker 불러오기
    private void callDatePickerDialog(View view) {
        DatePickerFragment pickerFragment = DatePickerFragment.getInstance();
        pickerFragment.show(getSupportFragmentManager(), pickerFragment.getTag());
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);

    }

    private void addBottomSheetDialog() {
        sidoBottomSheetFragment = SidoBottomSheetFragment.getInstance(this);
        sidoBottomSheetFragment.show(getSupportFragmentManager(), SidoBottomSheetFragment.class.getName());
    }

    @Override
    public void onItemClick(String sidoName) {
        binding.sidoBtn.setText(sidoName);
        sidoBottomSheetFragment.dismiss();
    }

}