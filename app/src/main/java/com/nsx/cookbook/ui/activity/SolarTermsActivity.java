package com.nsx.cookbook.ui.activity;

import android.widget.GridView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.adapter.FestivalGridViewAdapter;
import com.nsx.cookbook.base.BaseActivity;
import com.nsx.cookbook.bean.custom.Festival;
import com.nsx.cookbook.model.LocalJsonResolutionUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by loptop on 2017/5/9.
 */

public class SolarTermsActivity extends BaseActivity {

    @BindView(R.id.solar_grid_view)
    GridView mSolarGridView;

    @Override
    protected void initView() {
        //得到本地json文本内容
        String fileName = "solarterms.json";
        String festivaljson = LocalJsonResolutionUtils.getJson(this, fileName);
        //转换为对象
        Festival festival = LocalJsonResolutionUtils.JsonToObject(festivaljson, Festival.class);
        FestivalGridViewAdapter adapter = new FestivalGridViewAdapter(this, festival.getList());
        mSolarGridView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_solar_terms;
    }


    @OnClick(R.id.solar_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
