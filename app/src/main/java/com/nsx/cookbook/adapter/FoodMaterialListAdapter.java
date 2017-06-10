package com.nsx.cookbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.bean.FoodDetailBean.ResultBean.ListBean.MaterialBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FoodMaterialListAdapter extends BaseAdapter {
    Context mContext;
    List<MaterialBean> mList;


    public FoodMaterialListAdapter(Context context, List<MaterialBean> list) {
        this.mContext = context;
        this.mList = list;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fooddetail_material, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置食物制作的材料明细
        MaterialBean materialBean = mList.get(position);
        //相同类别才 显示
        holder.material_name.setText(mList.get(position).getMname());
        holder.material_count.setText(mList.get(position).getAmount());
        return convertView;
    }

    class ViewHolder {
        //食物材料名字
        @BindView(R.id.tv_material_name)
        TextView material_name;
        //食物材料数量
        @BindView(R.id.tv_material_count)
        TextView material_count;

        public ViewHolder(View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }
}
