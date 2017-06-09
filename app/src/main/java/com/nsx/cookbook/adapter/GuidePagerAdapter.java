package com.nsx.cookbook.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nsx.cookbook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */

public class GuidePagerAdapter extends PagerAdapter{
    //引导页的 页面集合
    private List<ImageView> mImageViews = new ArrayList<>();

    public GuidePagerAdapter(Context context){
        //得到引导页页面集合
        for (int i = 0;i < 4;i++) {
            ImageView imageView = new ImageView(context);
            try {
                imageView.setImageResource(R.mipmap.class.getField(
                        "ic_welcom" + (i + 1)).getInt(null));
                //充满
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                mImageViews.add(imageView);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getCount() {
        return mImageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImageViews.get(position));
        return mImageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImageViews.get(position));
    }
}
