package com.project.housing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.housing.adapter.HousingListAdapter;
import com.project.housing.databinding.ActivityHousingListBinding;
import com.project.housing.models.request.ReqHousingList;
import com.project.housing.models.response.Item;
import com.project.housing.models.response.Items;
import com.project.housing.models.response.Response;
import com.project.housing.repository.HousingService;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HousingListActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private HousingListAdapter adapter;
    private ActivityHousingListBinding binding;
    private ReqHousingList housingList;
    private HousingService service;
    private List<Item> itemList;
    // 스크롤 페이징 처리를 위한 변수
    private int currentPageNumber = 1;
    private boolean duplicateScrollEvent = true;
    private boolean isGetSecondData = true;
    private int totalCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHousingListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getAPTListData();
        initData();
        addBackButtonEventListener();
    }

    private void initData() {
        service = HousingService.retrofit.create(HousingService.class);
    }

    // APT List data 뿌려주기
    private void getAPTListData() {
        if (getIntent() != null) {
            Items items = (Items) getIntent().getSerializableExtra("serialHousingListObj");
            housingList = (ReqHousingList) getIntent().getSerializableExtra("serialReqObj");
            totalCount = getIntent().getIntExtra("totalCount", 0);
            Log.d(TAG, "total count : " + totalCount);
            if (housingList.getSidoName() == null) {
                connectRecyclerViewAndAdapter(items, true);
                setTopAppBar(true);
                currentPageNumber++;
            } else {
                connectRecyclerViewAndAdapter(items, false);
                setTopAppBar(false);
                currentPageNumber++;
            }
        }
    }

    // RecyclerView & Adapter 연결
    private void connectRecyclerViewAndAdapter(Items items, Boolean isEmptySidoName) {
        List<Item> itemList = items.getItem();

        adapter = new HousingListAdapter(this, itemList, isEmptySidoName);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        binding.recyclerView.setLayoutManager(manager);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.hasFixedSize();
        binding.recyclerView.setOnScrollChangeListener((view, i, i1, i2, i3) -> {
            if (duplicateScrollEvent) { // true 일때 실행 반복
                int lastVisibleItemPosition = ((LinearLayoutManager) binding.recyclerView.getLayoutManager())
                        .findLastVisibleItemPosition(); // 마지막 item 의 index 값을 가져오기 위해 변수로 지정

                int itemTotalCount = binding.recyclerView.getAdapter().getItemCount() - 1;

                if (lastVisibleItemPosition == itemTotalCount) {
                    Log.d(TAG, "마지막 아이템에 도달했습니다.");
                    if (currentPageNumber != 1) {
                        requestItemListData(currentPageNumber);
                        duplicateScrollEvent = false;
                        if ((totalCount / 10) + 1 >= currentPageNumber) {
                            duplicateScrollEvent = false;
                        }
                    }
                }
            }
        });
    }

    // topAppBar 에 데이터 세팅
    private void setTopAppBar(Boolean isEmptySidoName) {
        if (isEmptySidoName) {
            binding.topAppBar.sidoTv.setText("전국");
        } else {
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

    private void requestItemListData(int pageNumber) {
        service.getHousingList(HousingService.decodingKey_J, housingList.getStartMonth(),
                housingList.getEndMonth(), housingList.getSidoName(), pageNumber).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                itemList = response.body().getBody().getItems().getItem();
                if (itemList != null) {
                    Log.d(TAG, "item : " + itemList);
                    adapter.addItem(itemList);

                    currentPageNumber++;
                    duplicateScrollEvent = true;
                } else {
                    duplicateScrollEvent = false;
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                Toast.makeText(HousingListActivity.this, "데이터 통신 오류입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}