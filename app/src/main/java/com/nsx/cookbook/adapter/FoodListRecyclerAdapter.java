package com.nsx.cookbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nsx.cookbook.R;
import com.nsx.cookbook.app.AppApplication;
import com.nsx.cookbook.base.NoDoubleClickListener;
import com.nsx.cookbook.bean.FoodDetailBean.ResultBean.ListBean;
import com.nsx.cookbook.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by loptop on 2017/4/23.
 */

public class FoodListRecyclerAdapter extends RecyclerView.Adapter<FoodListRecyclerAdapter.ViewHolder> {

    private Context mContext;

    private List<ListBean> mList;

    public FoodListRecyclerAdapter(Context context, List<ListBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_foodlist_recyclerview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //Glide设置图片(未加载则用橘黄色替代)
        final String imageUrl = mList.get(position).getPic();
        Glide.with(mContext)
                .load(imageUrl)
                .asBitmap()
                .centerCrop()
                .placeholder(R.color.colorAccent)
                .into(holder.foodImage);
        holder.foodName.setText(StringUtils.removeOtherCode(mList.get(position).getName()));
        //将数据保存在mImageView的TAG中，以便需要时取出
        holder.foodImage.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                //如果接口对象不为空
                if (mOnItemClickListener != null) {
                    //全局共享数据
                    AppApplication.getInstance().setListBean(mList.get(position));
                    //将点击事件转移给自定义的接口
                    mOnItemClickListener.onItemClick();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();

    }

    //    //添加数据 用于下拉刷新
//    public void addData(List<GirlsBean.ShowapiResBodyBean.NewslistBean> newDatas) {
//        //向一个新的集合，添加以前所有的数据
//        newDatas.addAll(mList);
//        //以前集合的数据全部移除
//        mList.removeAll(mList);
//        //添加最新的集合里的数据
//        mList.addAll(newDatas);
//        //数据改变，通知RecyclerView改变视图
//        notifyDataSetChanged();
//    }
//
    //用于上拉加载
    public void addMoreItem(List<ListBean> newDatas) {
        mList.addAll(newDatas);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick();
    }

    public void setList(List<ListBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    //声明接口变量
    private OnItemClickListener mOnItemClickListener = null;

    //暴露接口给外部
    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        this.mOnItemClickListener = OnItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_food)
        ImageView foodImage;
        @BindView(R.id.tv_food_name)
        TextView foodName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
