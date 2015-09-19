package com.example.dennyhsiao.recyclerviewtest;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    CardView mCardView;
    TextView mTitleText;
    TextView mSummaryText;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mCardView = (CardView) itemView.findViewById(R.id.item_card_view);
        mTitleText = (TextView) itemView.findViewById(R.id.item_text_title);
        mSummaryText = (TextView) itemView.findViewById(R.id.item_text_summary);
    }
}
