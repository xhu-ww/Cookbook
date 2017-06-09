package com.nsx.cookbook.ui.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.nsx.cookbook.R;
import com.nsx.cookbook.adapter.ViewPagerAdapter;
import com.nsx.cookbook.app.AppApplication;
import com.nsx.cookbook.base.BaseActivity;
import com.nsx.cookbook.ui.fragment.HomeFragment;
import com.nsx.cookbook.ui.fragment.LoveFragment;
import com.nsx.cookbook.ui.fragment.PersonFragment;
import com.nsx.cookbook.ui.fragment.FindFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    //退出App第一次按退出键的时间
    private long startTime;
    @BindView(R.id.rg_tab)
    RadioGroup mRgTab;
    @BindView(R.id.main_viewpager)
    ViewPager mViewpager;

    @Override
    protected void initView() {
        mRgTab.setOnCheckedChangeListener(this);
        List<Fragment> fragments = getFragments();
        //ViewPager设置适配器
        mViewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        //默认选中首页界面
        mViewpager.setCurrentItem(0);
        //页面改变监听
        mViewpager.addOnPageChangeListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                //去除ViewPager切换动画
                mViewpager.setCurrentItem(0,false);
                break;
            case R.id.rb_search:
                mViewpager.setCurrentItem(1,false);
                break;
            case R.id.rb_love:
                mViewpager.setCurrentItem(2,false);
                break;
            case R.id.rb_person:
                mViewpager.setCurrentItem(3,false);
                break;
        }
    }

    /**
     * 得到主界面需要的Fragment
     *
     * @return
     */
    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        //首页
        fragments.add(new HomeFragment());
        //发现
        fragments.add(new FindFragment());
        //最爱
        fragments.add(new LoveFragment());
        //我的
        fragments.add(new PersonFragment());
        return fragments;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mRgTab.check(R.id.rb_home);
                break;
            case 1:
                mRgTab.check(R.id.rb_search);
                break;
            case 2:
                mRgTab.check(R.id.rb_love);
                break;
            case 3:
                mRgTab.check(R.id.rb_person);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 两次退出App
     */
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - startTime < 2000) {
            AppApplication.getInstance().finishAll();
        } else {
            showToast("再按一次退出");
            startTime = System.currentTimeMillis();
        }
    }
}
