package com.example.dennyhsiao.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<MainRecyclerViewActivity.TaskItemEntry> mNonGroupData;
    public RecyclerViewAdapter(Context context) {
        this(context,new ArrayList<MainRecyclerViewActivity.TaskItemEntry>());
    }

    public RecyclerViewAdapter(Context context,List<MainRecyclerViewActivity.TaskItemEntry> data) {
        mContext = context;
        mNonGroupData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        return new RecyclerViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof RecyclerViewHolder) {
            MainRecyclerViewActivity.TaskItemEntry taskItemEntry = mNonGroupData.get(position);
            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) viewHolder;
            recyclerViewHolder.titleText.setText(taskItemEntry.title);
            recyclerViewHolder.summaryText.setText(taskItemEntry.summary);
        }
    }

    @Override
    public int getItemCount() {
        return mNonGroupData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return ITEM_TYPE.LIST_ITEM.ordinal();
    }

    public List<MainRecyclerViewActivity.TaskItemEntry> getData() {
        return mNonGroupData;
    }

    public void setData(List<MainRecyclerViewActivity.TaskItemEntry> data) {
        mNonGroupData = data;
    }

    public enum ITEM_TYPE {
        LIST_ITEM
    }
}
