package com.project.housing;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.project.housing.adapter.SidoItemAdapter;
import com.project.housing.databinding.FragmentBottomSheetSidoBinding;
import com.project.housing.interfaces.OnSidoItemClickListener;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SidoBottomSheetFragment extends BottomSheetDialogFragment {

    private FragmentBottomSheetSidoBinding binding;
    private SidoItemAdapter adapter;

    private static SidoBottomSheetFragment sidoBottomSheetFragment;
    private static OnSidoItemClickListener onSidoItemClickListener;

    private SidoBottomSheetFragment(OnSidoItemClickListener onSidoItemClickListener) {
        this.onSidoItemClickListener = onSidoItemClickListener;
    }

    public static SidoBottomSheetFragment getInstance(OnSidoItemClickListener onSidoItemClickListener) {
        if (sidoBottomSheetFragment == null) {
            sidoBottomSheetFragment = new SidoBottomSheetFragment(onSidoItemClickListener);
        }
        return sidoBottomSheetFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBottomSheetSidoBinding.inflate(inflater, container, false);
        adapter = new SidoItemAdapter(getContext(), onSidoItemClickListener);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = binding.sidoListRecyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}