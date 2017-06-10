package com.nsx.cookbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.bean.FoodCategoryBean;
import com.nsx.cookbook.ui.activity.FoodListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<FoodCategoryBean.ResultBean.ListBean> mList;

    public GridViewAdapter(Context context, List<FoodCategoryBean.ResultBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setList(List<FoodCategoryBean.ResultBean.ListBean> list) {
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gridview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //当前菜类别的名字
        final String foodName = mList.get(position).getName();
        //当前菜类别的ID
        final String foodId = mList.get(position).getClassid();
        holder.tv_name.setText(foodName);
        //点击事件
        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //带参跳转界面
                Intent intent = new Intent(mContext, FoodListActivity.class);
                //传递数组
                intent.putExtra("FOOD", new String[]{foodName, foodId});
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_foodcategory_name)
        TextView tv_name;

        public ViewHolder(View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }
}
