package com.project.housing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.housing.adapter.HousingListAdapter;
import com.project.housing.databinding.ActivityHousingListBinding;
import com.project.housing.models.request.ReqHousingList;
import com.project.housing.models.response.Item;
import com.project.housing.models.response.Items;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HousingListActivity extends AppCompatActivity {

    private HousingListAdapter adapter;
    private ActivityHousingListBinding binding;
    private ReqHousingList housingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHousingListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getAPTListData();
        addBackButtonEventListener();
    }

    // APT List data 뿌려주기
    private void getAPTListData() {
        if (getIntent() != null) {
            Items items = (Items) getIntent().getSerializableExtra("serialHousingListObj");
            housingList = (ReqHousingList) getIntent().getSerializableExtra("serialReqObj");
            if (housingList.getSidoName() == null){
                connectRecyclerViewAndAdapter(items, true);
                setTopAppBar(true);
            }else{
                connectRecyclerViewAndAdapter(items, false);
                setTopAppBar(false);

            }
        }
    }

    // RecyclerView & Adapter 연결
    private void connectRecyclerViewAndAdapter(Items items, Boolean isEmptySidoName){
        List<Item> itemList = items.getItem();

        adapter = new HousingListAdapter(this, itemList, isEmptySidoName);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.hasFixedSize();
    }

    // topAppBar 에 데이터 세팅
    private void setTopAppBar(Boolean isEmptySidoName){
        if (isEmptySidoName){
            binding.topAppBar.sidoTv.setText("전국");
        }else{
            binding.topAppBar.sidoTv.setText(housingList.getSidoName());
        }
        binding.topAppBar.startDateTv.setText(housingList.getStartMonth());
        binding.topAppBar.endDateTv.setText(housingList.getEndMonth());
    }

    // 뒤로가기 버튼 이벤트 리스너
    private void addBackButtonEventListener() {
        binding.topAppBar.backBtn.setOnClickListener(v -> {
            finish();
        });
    }
}