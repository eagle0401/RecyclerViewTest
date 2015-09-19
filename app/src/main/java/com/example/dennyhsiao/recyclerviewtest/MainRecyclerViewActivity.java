package com.example.dennyhsiao.recyclerviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainRecyclerViewActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private List<TaskItemEntry> mNonGroupData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler_view);
        initData();
        initAdapter();
        initRecyclerView();
    }

    private void initData() {
        String[] titleArray = new String[]{"titleA", "titleB", "titleC", "titleD", "titleE", "titleF"};
        String[] summaryArray = new String[]{"summaryA", "summaryB", "summaryC", "summaryD", "summaryE", "summaryF"};
        mNonGroupData = new ArrayList<>();
        for (int i = 0; i < titleArray.length; i++) {
            TaskItemEntry taskItemEntry = new TaskItemEntry();
            taskItemEntry.title = titleArray[i];
            taskItemEntry.summary = summaryArray[i];
            mNonGroupData.add(taskItemEntry);
        }
    }

    private void initAdapter() {
        mRecyclerViewAdapter = new RecyclerViewAdapter(this);
        mRecyclerViewAdapter.setData(mNonGroupData);
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView view, int scrollState) {
                switch (scrollState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        Log.d("test", "scrollState = RecyclerView.SCROLL_STATE_IDLE");
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        Log.d("test", "scrollState = RecyclerView.SCROLL_STATE_DRAGGING");
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        Log.d("test", "scrollState = RecyclerView.SCROLL_STATE_SETTLING");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView view, int dx, int dy) {
                Log.d("test", "onScrolled RecyclerView = " + view + "at dx = " + dx + ", dy = " + dy);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                mNonGroupData = mRecyclerViewAdapter.getData();
                int startHolderPosition = viewHolder.getAdapterPosition();
                int endHolderPosition = target.getAdapterPosition();
                Log.d("test", "onMove view = " + recyclerView + ", startHolderPosition = "
                        + startHolderPosition + ", endHolderPosition = " + endHolderPosition);

                if (startHolderPosition > endHolderPosition) {
                    for (int i = startHolderPosition; i > endHolderPosition + 1; i--) {
                        Collections.swap(mNonGroupData, i, i - 1);
                    }
                } else {
                    for (int i = startHolderPosition; i < endHolderPosition; i++) {
                        Collections.swap(mNonGroupData, i, i + 1);
                    }
                }
                mRecyclerViewAdapter.notifyItemMoved(startHolderPosition, endHolderPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                switch (direction) {
                    case ItemTouchHelper.UP:
                        Log.d("test", "onSwiped => direction = UP");
                        break;
                    case ItemTouchHelper.DOWN:
                        Log.d("test", "onSwiped => direction = DOWN");
                        break;
                    case ItemTouchHelper.LEFT:
                        Log.d("test", "onSwiped => direction = LEFT");
                        break;
                    case ItemTouchHelper.RIGHT:
                        Log.d("test", "onSwiped => direction = RIGHT");
                        break;
                    default:
                        Log.d("test", "onSwiped => direction = " + direction);
                }

            }
        });

        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    public class TaskItemEntry {
        public String title;
        public String summary;
    }
}
