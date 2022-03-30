package com.project.housing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.project.housing.databinding.ActivityHousingDetailBinding;
import com.project.housing.models.response.Item;

import java.util.List;

public class HousingDetailActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private ActivityHousingDetailBinding binding;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHousingDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent() != null){
            item = (Item) getIntent().getSerializableExtra("detailObj");
            Log.d(TAG, item.toString());
        }
    }

    private void addBackButtonEventListener(){
        binding.detailAppBar.backBtn.setOnClickListener(view -> {
            finish();
        });
    }
}