package com.nsx.cookbook.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.bean.custom.ChatMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */

public class ChatListViewAdapter extends BaseAdapter {

    public static final int LEFT = 1;
    public static final int RIGHT = 0;

    public static long time;

    private Context mContext;
    private List<ChatMessage> mList;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ChatListViewAdapter(Context context, List<ChatMessage> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setList(List<ChatMessage> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        switch (getItemViewType(position)) {
            case RIGHT:
                convertView = View.inflate(mContext, R.layout.item_chat_right, null);
                holder = new ViewHolder(convertView);
                break;
            case LEFT:
                convertView = View.inflate(mContext, R.layout.item_chat_left, null);
                holder = new ViewHolder(convertView);
                break;
        }

        String content = mList.get(position).getContent();

        holder.tv_content.setText(content);

        holder.tv_time.setText(format.format(new Date(mList.get(position).getTime())));
        if (position == 0)
            time = mList.get(position).getTime();//第一条记录的 时间
        else {
            if (mList.get(position).getTime() - time < 10000) {
                time = mList.get(position).getTime();
                holder.tv_time.setVisibility(View.GONE);
            } else {
                time = mList.get(position).getTime();
                holder.tv_time.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }


    class ViewHolder {
        TextView tv_content;
        TextView tv_time;

        public ViewHolder(View convertView) {
            tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            tv_time = (TextView) convertView.findViewById(R.id.tv_time);
        }
    }

    public int getItemViewType(int position) {
        return mList.get(position).isMe() ? RIGHT : LEFT;
    }
}
