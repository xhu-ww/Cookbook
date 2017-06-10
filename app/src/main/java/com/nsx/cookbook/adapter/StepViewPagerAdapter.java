package com.nsx.cookbook.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nsx.cookbook.R;
import com.nsx.cookbook.bean.FoodDetailBean.ResultBean.ListBean.ProcessBean;

import java.util.ArrayList;
import java.util.List;


public class StepViewPagerAdapter extends PagerAdapter {
    //外部传递图片网址集合
    List<ImageView> mViews = new ArrayList<>();

    public StepViewPagerAdapter(Context context, List<ProcessBean> list) {
        for (ProcessBean processBean : list) {

            ImageView imageView = new ImageView(context);
            //图片地址
            String imageUrl = processBean.getPic();
            //设置图片
            Glide.with(context)
                    .load(imageUrl)
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.color.colorAccent)
                    .into(imageView);
            //将ImageView加入集合
            mViews.add(imageView);
        }
    }


    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}

