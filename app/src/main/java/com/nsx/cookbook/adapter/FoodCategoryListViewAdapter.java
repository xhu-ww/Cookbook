package com.nsx.cookbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.bean.FoodCategoryBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/21.
 */

public class FoodCategoryListViewAdapter extends BaseAdapter {
    //当前Item被点击的位置
    private int currentItem;
    private Context mContext;
    private List<FoodCategoryBean.ResultBean> mList;

    public FoodCategoryListViewAdapter(Context context, List<FoodCategoryBean.ResultBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置文字
        holder.foodcategory.setText(mList.get(position).getName());
        if (currentItem == position) {
            holder.foodcategory.setSelected(true);
        } else {
            holder.foodcategory.setSelected(false);
        }
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_foodcategory_left)
        TextView foodcategory;

        public ViewHolder(View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }
}
