package com.nsx.cookbook.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.app.AppApplication;
import com.nsx.cookbook.base.BaseFragment;
import com.nsx.cookbook.bean.custom.LocalCalendar;
import com.nsx.cookbook.ui.activity.ChatActivity;
import com.nsx.cookbook.ui.activity.FestivalActivity;
import com.nsx.cookbook.ui.activity.FoodListActivity;
import com.nsx.cookbook.ui.activity.HistoryActivity;
import com.nsx.cookbook.ui.activity.SolarTermsActivity;
import com.nsx.cookbook.utils.DialogHelper;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/4/17.
 */

public class FindFragment extends BaseFragment {
    //公历
    @BindView(R.id.tv_gregorian_calendar)
    TextView mTvGregorianCalendar;
    //农历
    @BindView(R.id.tv_chinese_calendar)
    TextView mTvChineseCalendar;
    //节气
    @BindView(R.id.tv_solar_terms)
    TextView mTvSolarTerms;
    //宜做的事情
    @BindView(R.id.tv_luck)
    TextView mTvLuck;
    //不宜做的事情
    @BindView(R.id.tv_unlucky)
    TextView mTvUnlucky;

    //传递 食物分类的ID 给 FoodListActivity
    private Intent intentfood;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initview() {
        intentfood = new Intent(mActivity, FoodListActivity.class);
        getCalendarData();
    }


    @OnClick({R.id.food_solar_terms, R.id.food_holiday, R.id.food_for_man, R.id.food_for_woman,
            R.id.food_for_brain, R.id.ll_chat, R.id.ll_history, R.id.ll_luck, R.id.ll_unlucky})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.food_solar_terms:
                startActivity(new Intent(mActivity, SolarTermsActivity.class));
                break;
            case R.id.food_holiday:
                startActivity(new Intent(mActivity, FestivalActivity.class));
                break;
            case R.id.food_for_man:
                //传递数组
                intentfood.putExtra("FOOD", new String[]{"男性养生", "141"});
                startActivity(intentfood);
                break;
            case R.id.food_for_woman:
                intentfood.putExtra("FOOD", new String[]{"女性养生", "142"});
                startActivity(intentfood);
                break;
            case R.id.food_for_brain:
                intentfood.putExtra("FOOD", new String[]{"脑力补充", "140"});
                startActivity(intentfood);
                break;
            case R.id.ll_chat:
                startActivity(new Intent(mActivity, ChatActivity.class));
                break;
            case R.id.ll_history:
                startActivity(new Intent(mActivity, HistoryActivity.class));
                break;
            case R.id.ll_luck:
                //弹出对话框
                DialogHelper.getMessageDialog(mActivity, "宜:" + mTvLuck.getText().toString()).show();
                break;
            case R.id.ll_unlucky:
                DialogHelper.getMessageDialog(mActivity, "忌:" + mTvUnlucky.getText().toString()).show();
                break;
        }
    }

    /**
     * 从数据库得到日期数据
     */
    private void getCalendarData() {
        LocalCalendar calendar = AppApplication.getInstance().getLocalDate();
        if (calendar != null) {
            mTvGregorianCalendar.setText(calendar.getGregorianCalendar());
            mTvChineseCalendar.setText(calendar.getChineseCalendar());
            mTvSolarTerms.setText(calendar.getSolarTerms());
            mTvLuck.setText(calendar.getLuck());
            mTvUnlucky.setText(calendar.getUnlucky());
        }
    }


}
