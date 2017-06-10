package com.nsx.cookbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nsx.cookbook.R;
import com.nsx.cookbook.base.NoDoubleClickListener;
import com.nsx.cookbook.bean.custom.FoodCollection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FoodLoveGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<FoodCollection> mList;

    public FoodLoveGridViewAdapter(Context context, List<FoodCollection> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setList(List<FoodCollection> list) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_love_gridview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //当前菜的ID 点击时传递出去
        final String id = mList.get(position).getId();
        //Glide设置图片(未加载则用橘黄色替代)
        final String imageUrl = mList.get(position).getImage();
        Glide.with(mContext)
                .load(imageUrl)
                .asBitmap()
                .centerCrop()
                .placeholder(R.color.colorAccent)
                .into(holder.foodImage);
        holder.foodName.setText(mList.get(position).getName());
        //将数据保存在mImageView的TAG中，以便需要时取出
        holder.foodImage.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                //如果接口对象不为空
                if (mOnItemClickListener != null) {
                    //将点击事件转移给自定义的接口
                    mOnItemClickListener.onItemClick(id);
                }
            }
        });
        return convertView;
    }

    public class ViewHolder {
        @BindView(R.id.iv_love_food)
        ImageView foodImage;
        @BindView(R.id.tv_love_foodname)
        TextView foodName;

        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

    //自定义的接口
    public interface OnItemClickListener {
        void onItemClick(String data);
    }

    //声明接口变量
    private OnItemClickListener mOnItemClickListener = null;

    //暴露接口给外部
    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        this.mOnItemClickListener = OnItemClickListener;
    }
}
