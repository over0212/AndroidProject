package com.project.housing;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

        setStatusBar();
        getDetailData();
        addBackButtonEventListener();
    }

    private void setStatusBar(){
        getWindow().setStatusBarColor(Color.WHITE);
    }

    private void getDetailData() {
        if (getIntent() != null) {
            item = (Item) getIntent().getSerializableExtra("detailObj");
            Log.d(TAG, item.toString());
            binding.detailAppBar.houseNMTv.setText(item.getHouseName());
            // 공고번호
            binding.noticeNumber.setText(item.getNoticeNumber());
            // 주택명
            binding.houseName.setText(item.getHouseName());
            // 주택관리 번호
            binding.houseManageNumber.setText(item.getHouseManageNumber());
            // 주택구분
            binding.houseDetailSectionedName.setText(item.getHouseDetailSectionedName());
            // 분양 / 임대
            binding.rentOrSaleName.setText(item.getRentOrSaleName());
            // 건설업체
            binding.buildCompanyName.setText(item.getBuildCompanyName());
            // 공급 규모
            binding.totalSupply.setText(item.getTotalSupply());
            // 청약 접수 시작일, 종료일
            binding.receiptStartDate.setText(item.getReceiptStartDate());
            binding.receiptEndDate.setText(item.getReceiptEndDate());
            // 당첨자 발표
            binding.winnerPresentDate.setText(item.getWinnerPresentDate());
            // 계약 시작일, 종료일
            binding.startContract.setText(item.getStartContract());
            binding.endContract.setText(item.getEndContract());
            // 1순위, 2순위 해당지역
            if (item.getRnk1CrspArea() == null || item.getRnk2CrspArea() == null) {
                binding.box8Rnk11.setVisibility(View.GONE);
                binding.box9Rnk21.setVisibility(View.GONE);
            } else {
                binding.rnk1CrspArea.setText(item.getRnk1CrspArea());
                binding.rnk2CrspArea.setText(item.getRnk2CrspArea());
            }
            // 1순위, 2순위 경기지역
            if (item.getRnk1EtcGg() == null || item.getRnk2CrspArea() == null) {
                binding.box8Rnk12.setVisibility(View.GONE);
                binding.box9Rnk22.setVisibility(View.GONE);
            } else {
                binding.rnk1EtcGg.setText(item.getRnk1EtcGg());
                binding.rnk2EtcGg.setText(item.getRnk2EtcGg());
            }
            // 1순위, 2순위 기타지역
            if (item.getRnk1EtcArea() == null) {
                binding.box8Rnk13.setVisibility(View.GONE);
                binding.box9Rnk23.setVisibility(View.GONE);
            } else {
                binding.rnk1EtcArea.setText(item.getRnk1EtcArea());
                binding.rnk2EtcArea.setText(item.getRnk2EtcArea());
            }
            // 특별 공급 접수 시작일, 종료일
            if (item.getSpecialReceiptStartDate() == null || item.getSpecialReceiptEndDate() == null) {
                binding.box10Special1.setVisibility(View.GONE);
                binding.box10Special2.setVisibility(View.GONE);
            } else {
                binding.specialReceiptStartDate.setText(item.getSpecialReceiptStartDate());
                binding.specialReceiptEndDate.setText(item.getSpecialReceiptEndDate());
            }
            // 홈페이지
            if (item.getHomepageAddress() == null) {
                binding.box11Homepage.setVisibility(View.GONE);
            } else {
                binding.homepageAddress.setText(item.getHomepageAddress());
            }
            // 공급주소
            binding.housePlaceAddr.setText(item.getHouseAddress());
        }
    }

    private void addBackButtonEventListener() {
        binding.detailAppBar.backBtn.setOnClickListener(view -> {
            finish();
        });
    }
}