package com.nsx.cookbook.ui.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.nsx.cookbook.R;
import com.nsx.cookbook.adapter.HistoryRecyclerViewAdapter;
import com.nsx.cookbook.base.BaseActivity;
import com.nsx.cookbook.bean.TodayHistory;
import com.nsx.cookbook.interfaces.BeanCallBack;
import com.nsx.cookbook.model.TodayHistoryModel;
import com.nsx.cookbook.utils.NetUtils;
import com.nsx.cookbook.utils.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class HistoryActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.history_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.ll_history_main)
    LinearLayout mLinearLayout;
    @Override
    protected void initView() {
        //得到 今天的日期 2017-05-01
        String date = StringUtils.getYTDDateString(new Date());
        //然後按照"-"分开
        String month = date.split("-")[1];
        String day = date.split("-")[2];
        if (NetUtils.isConnected(this)) {
            requestData(month, day);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else {
            mLinearLayout.setBackgroundResource(R.mipmap.no_net);
            showToast("网络未连接");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history_today;
    }

    @OnClick(R.id.history_back)
    public void onViewClicked() {
        onBackPressed();
    }

    /**
     * 请求数据
     */
    private void requestData(String month, String day) {

        TodayHistoryModel model = TodayHistoryModel.getInstance();
        model.getTodayHistory(month, day, new BeanCallBack<TodayHistory>() {
            @Override
            public void onSucceed(TodayHistory todayHistory) {
                List<HashMap<String, String>> list = new ArrayList<>();
                for (TodayHistory.ResultBean resultBean : todayHistory.getResult()) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("date", resultBean.getYear());
                    map.put("title", resultBean.getTitle());
                    list.add(map);
                }
                HistoryRecyclerViewAdapter adapter = new HistoryRecyclerViewAdapter(list);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String msg) {
                showToast(msg);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
