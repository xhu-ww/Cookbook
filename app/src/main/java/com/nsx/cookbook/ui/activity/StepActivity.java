package com.nsx.cookbook.ui.activity;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.adapter.StepViewPagerAdapter;
import com.nsx.cookbook.app.AppApplication;
import com.nsx.cookbook.base.BaseActivity;
import com.nsx.cookbook.bean.FoodDetailBean.ResultBean.ListBean;
import com.nsx.cookbook.bean.FoodDetailBean.ResultBean.ListBean.ProcessBean;
import com.nsx.cookbook.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/24.
 */

public class StepActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.step_title)
    TextView mStepTitle;
    @BindView(R.id.step_viewpager)
    ViewPager mStepViewpager;
    @BindView(R.id.step_number)
    TextView mStepNumber;
    @BindView(R.id.step_describe)
    TextView mStepDescribe;
    //步骤描述集合
    private List<String> steps = new ArrayList<>();

    @Override
    protected void initView() {
        //得到上个界面 步骤 点击的位置
        int position = Integer.parseInt(getIntent().getStringExtra("POSITION"));
        ListBean mListBean = AppApplication.getInstance().getListBean();
        List<ProcessBean> list = mListBean.getProcess();
        //菜品的名字
        String foodName = mListBean.getName();

        mStepTitle.setText(foodName);
        //适配器
        StepViewPagerAdapter adapter = new StepViewPagerAdapter(this, list);
        mStepViewpager.setAdapter(adapter);
        for (ProcessBean processBean : list) {
            steps.add(processBean.getPcontent());
        }
        mStepViewpager.setCurrentItem(position);
        //设置步骤描述内容
        mStepNumber.setText(position + 1 + "/" + steps.size());

        mStepDescribe.setText(StringUtils.removeOtherStr(steps.get(position)));
        //ViewPager滑动事件
        mStepViewpager.addOnPageChangeListener(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_step;
    }


    @OnClick(R.id.step_back)
    public void onViewClicked() {
        //返回上一页
        onBackPressed();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //设置步骤描述内容
        mStepNumber.setText(position + 1 + "/" + steps.size());
        mStepDescribe.setText(StringUtils.removeOtherStr(steps.get(position)));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
