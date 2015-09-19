package com.example.dennyhsiao.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView titleText;
    TextView summaryText;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        titleText = (TextView) itemView.findViewById(R.id.itemTextTitle);
        summaryText = (TextView) itemView.findViewById(R.id.itemTextSummary);
    }
}
