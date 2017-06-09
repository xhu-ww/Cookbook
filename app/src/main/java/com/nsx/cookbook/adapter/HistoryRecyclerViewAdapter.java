package com.nsx.cookbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.nsx.cookbook.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.TimeLineViewHolder> {

    List<HashMap<String, String>> mList;

    public HistoryRecyclerViewAdapter(List<HashMap<String, String>> list) {
        this.mList = list;
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_timeline, null);
        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        holder.title.setText(mList.get(position).get("title"));
        holder.date.setText(mList.get(position).get("date"));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }


    public class TimeLineViewHolder extends RecyclerView.ViewHolder {
        public TimelineView mTimelineView;
        public TextView date;
        public TextView title;

        public TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);
            mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
            date = (TextView) itemView.findViewById(R.id.tv_date);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            mTimelineView.initLine(viewType);
        }
    }
}
