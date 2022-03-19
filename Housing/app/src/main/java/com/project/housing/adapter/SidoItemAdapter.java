package com.project.housing.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.housing.R;
import com.project.housing.databinding.FragmentSidoListItemBinding;
import com.project.housing.interfaces.OnSidoItemClickListener;

public class SidoItemAdapter extends RecyclerView.Adapter<SidoItemAdapter.ViewHolder> {

    private final Context context;
    private FragmentSidoListItemBinding binding;
    private final OnSidoItemClickListener onSidoItemClickListener;
    private static final String[] SIDO = {"전국", "서울", "인천", "광주", "세종", "대전", "대구", "울산",
            "부산", "제주", "경기", "충북", "충남", "전북", "전남", "강원", "경북", "경남"};

    public SidoItemAdapter(Context context, OnSidoItemClickListener onSidoItemClickListener) {
        this.onSidoItemClickListener = onSidoItemClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = FragmentSidoListItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sidoItemTv.setText(SIDO[position]);

        holder.itemView.setOnClickListener(view -> {
            onSidoItemClickListener.onItemClick(String.valueOf(holder.sidoItemTv.getText()));
        });
    }

    @Override
    public int getItemCount() {
        return SIDO.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView sidoItemTv;

        public ViewHolder(View itemView) {
            super(itemView);
            sidoItemTv = itemView.findViewById(R.id.sidoItemTv);
        }

    }
}
