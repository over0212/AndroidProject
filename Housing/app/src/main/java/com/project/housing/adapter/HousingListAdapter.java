package com.project.housing.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.housing.HousingDetailActivity;
import com.project.housing.R;
import com.project.housing.databinding.ActivityHousingListBinding;
import com.project.housing.models.request.ReqHousingList;
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

    public HousingListAdapter(Context context, List<Item> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
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
                    if (response.isSuccessful()){
                        Item item1 = (Item) response.body().getBody().getItems().getItem().get(0);
                        Intent intent = new Intent(context, HousingDetailActivity.class);
                        intent.putExtra("detailObj", item1);
                        context.startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {

                }
            });
        });

    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView companyNameTv;
        TextView houseNameTv;
        TextView recruitmentNoticeDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyNameTv = itemView.findViewById(R.id.companyNameTv);
            houseNameTv = itemView.findViewById(R.id.houseNameTv);
            recruitmentNoticeDate = itemView.findViewById(R.id.recruitmentNoticeDateTv);
        }

        private void setItem(Item item){
            companyNameTv.setText(item.getRentOrSaleName());
            houseNameTv.setText(item.getHouseName());
            recruitmentNoticeDate.setText(String.valueOf(item.getRecruitmentNoticeDate()));
        }
    }
}
