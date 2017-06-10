package com.nsx.cookbook.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.adapter.GuidePagerAdapter;
import com.nsx.cookbook.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.guide_viewpager)
    ViewPager mGuideViewpager;
    @BindView(R.id.guide_points)
    LinearLayout mGuidePoints;
    @BindView(R.id.btn_enter)
    TextView mBtnEnter;
    //ViewPager当前选中的位置
    private int currIndex;

    @Override
    protected void initView() {
        //新建4个View集合
        GuidePagerAdapter adapter = new GuidePagerAdapter(this);
        mGuideViewpager.setAdapter(adapter);
        initPoint();
        mGuideViewpager.addOnPageChangeListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }



    /**
     * 创建导航页的圆点
     */
    private void initPoint() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(12, 12);
        params.setMargins(10, 10, 10, 10);
        for (int i = 0; i < 4; i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.potin_selector);
            view.setLayoutParams(params);
            mGuidePoints.addView(view);
        }
        //drawable state_select 必须通过setSelected来改变。
        mGuidePoints.getChildAt(0).setSelected(true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //对圆点的控制
        mGuidePoints.getChildAt(currIndex).setSelected(false);
        mGuidePoints.getChildAt(position).setSelected(true);
        currIndex = position;
        //对按钮的控制
        if (position == 3) {
            //页面滑到最后一个页面时，显示进入按钮,点隐藏
            mBtnEnter.setVisibility(View.VISIBLE);
            mGuidePoints.setVisibility(View.GONE);
        } else {
            //返回前面页面时也的设置隐藏,点显示
            mBtnEnter.setVisibility(View.GONE);
            mGuidePoints.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @OnClick(R.id.btn_enter)
    public void onViewClicked() {
        //跳转主界面
        startActivity(MainActivity.class);
    }
}
