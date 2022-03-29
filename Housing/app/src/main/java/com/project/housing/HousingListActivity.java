package com.project.housing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.project.housing.adapter.HousingListAdapter;
import com.project.housing.databinding.ActivityHousingListBinding;
import com.project.housing.models.request.ReqHousingList;
import com.project.housing.models.response.Item;
import com.project.housing.models.response.Items;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HousingListActivity extends AppCompatActivity {

    private HousingListAdapter adapter;
    private ActivityHousingListBinding binding;
    private ReqHousingList housingList;
    private String startMonth;
    private String endMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHousingListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getAPTListData();
    }

    // APT List data 뿌려주기
    private void getAPTListData() {
        if (getIntent() != null) {
            Items items = (Items) getIntent().getSerializableExtra("serialHousingListObj");
            List<Item> itemList = items.getItem();

            adapter = new HousingListAdapter(this, itemList);
            LinearLayoutManager manager = new LinearLayoutManager(this);

            binding.recyclerView.setLayoutManager(manager);
            binding.recyclerView.setAdapter(adapter);
            binding.recyclerView.hasFixedSize();

            housingList = (ReqHousingList) getIntent().getSerializableExtra("serialReqObj");
            binding.topAppBar.startDateTv.setText(getIntent().getStringExtra("originSdate"));
            binding.topAppBar.endDateTv.setText(getIntent().getStringExtra("originEdate"));
            if (housingList.getSidoName() == null) {
                binding.topAppBar.sidoTv.setText("전국");
            } else {
                binding.topAppBar.sidoTv.setText(housingList.getSidoName());
            }
        }
    }
}