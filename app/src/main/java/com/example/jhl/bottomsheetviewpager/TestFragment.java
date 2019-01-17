package com.example.jhl.bottomsheetviewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TestFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    public static TestFragment newInstance() {

        Bundle args = new Bundle();

        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        initView();
        return view;
    }

    private void initView() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("列表"+i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAdapter = new RecyclerAdapter(list,getContext());
        recyclerView.setAdapter(recyclerAdapter);
    }
}
