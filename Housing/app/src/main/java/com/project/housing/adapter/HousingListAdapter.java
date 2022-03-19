package com.project.housing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.housing.R;
import com.project.housing.databinding.ActivityHousingListBinding;
import com.project.housing.models.request.ReqHousingList;
import com.project.housing.models.response.Item;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HousingListAdapter extends RecyclerView.Adapter<HousingListAdapter.ViewHolder> {

    Context context;
    List<Item> itemArrayList;

    public HousingListAdapter(Context context, List<Item> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_housing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemArrayList.get(position);
        holder.setItem(item);
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
            companyNameTv.setText(item.getBuildCompanyName());
            houseNameTv.setText(item.getHouseName());
            recruitmentNoticeDate.setText(String.valueOf(item.getRecruitmentNoticeDate()));
        }
    }
}
