package com.example.jhl.bottomsheetviewpager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class MyHolder extends RecyclerView.ViewHolder {

    public  TextView textView;

    public MyHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_text);
    }
}
