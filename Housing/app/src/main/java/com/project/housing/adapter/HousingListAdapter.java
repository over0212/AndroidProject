package com.project.housing.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.housing.HousingDetailActivity;
import com.project.housing.R;
import com.project.housing.models.response.Item;
import com.project.housing.models.response.Response;
import com.project.housing.repository.HousingService;
import com.project.housing.utils.Define;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HousingListAdapter extends RecyclerView.Adapter<HousingListAdapter.ViewHolder> {

    private static final String TAG = "TAG";
    private Context context;
    private List<Item> itemArrayList;
    private HousingService service;
    private Boolean isSearchAll;

    public HousingListAdapter(Context context, List<Item> itemArrayList, Boolean isSearchAll) {
        this.context = context;
        this.itemArrayList = itemArrayList;
        this.isSearchAll = isSearchAll;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_housing, parent, false);
        service = HousingService.retrofit.create(HousingService.class);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemArrayList.get(position);
        holder.setItem(item);
        String houseManageNum = item.getHouseManageNumber();
        String noticeNum = item.getNoticeNumber();

        holder.itemView.setOnClickListener(view -> {
            service.getAPTListDetail(HousingService.decodingKey_J, houseManageNum, noticeNum).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    Log.d(TAG, "response code : " + response.code());
                    if (response.isSuccessful()) {
                        Item data = response.body().getBody().getItems().getItem().get(0);
                        item.setStartContract(data.getStartContract());
                        item.setEndContract(data.getEndContract());
                        item.setRnk1CrspArea(data.getRnk1CrspArea());
                        item.setRnk1EtcGg(data.getRnk1EtcGg());
                        item.setRnk1EtcArea(data.getRnk1EtcArea());
                        item.setRnk2CrspArea(data.getRnk2CrspArea());
                        item.setRnk2EtcGg(data.getRnk2EtcGg());
                        item.setRnk2EtcArea(data.getRnk2EtcArea());
                        item.setHomepageAddress(data.getHomepageAddress());
                        item.setHouseAddress(data.getHouseAddress());
                        item.setSpecialReceiptStartDate(data.getSpecialReceiptStartDate());
                        item.setSpecialReceiptEndDate(data.getSpecialReceiptEndDate());
                        item.setTotalSupply(data.getTotalSupply());
                        Intent intent = new Intent(context, HousingDetailActivity.class);
                        intent.putExtra("detailObj", item);
                        context.startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Toast.makeText(context, "네트워크 통신 연결이 원활하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        LinearLayout listSidoContainer;
        TextView rentOrSaleNameTv;
        TextView sidoNameTv;
        TextView houseNameTv;
        TextView recruitmentNoticeDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            rentOrSaleNameTv = itemView.findViewById(R.id.rentOrSaleNameTv);
            listSidoContainer = itemView.findViewById(R.id.listSidoContainer);
            sidoNameTv = itemView.findViewById(R.id.sidoNameTv);
            houseNameTv = itemView.findViewById(R.id.houseNameTv);
            recruitmentNoticeDate = itemView.findViewById(R.id.recruitmentNoticeDateTv);
        }

        @SuppressLint("SetTextI18n")
        private void setItem(Item item) {
            if (isSearchAll){
                listSidoContainer.setVisibility(View.VISIBLE);
                sidoNameTv.setText(item.getSido());
            }else{
                listSidoContainer.setVisibility(View.GONE);
            }

            rentOrSaleNameTv.setText(item.getRentOrSaleName());
            houseNameTv.setText(item.getHouseName());

            String noticeDate = item.getRecruitmentNoticeDate();
            String noticeDateYear = noticeDate.substring(0, 4);
            String noticeDateMonth = noticeDate.substring(4, 6);
            String noticeDateDay = noticeDate.substring(6);
            recruitmentNoticeDate.setText(noticeDateYear + "." + noticeDateMonth + "." + noticeDateDay);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addItem(List<Item> addList){
        itemArrayList.addAll(itemArrayList.size(), addList);
        notifyDataSetChanged();
    }
}
