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

public class FestivalActivity extends BaseActivity {
    @BindView(R.id.festival_grid_view)
    GridView mFestivalGridView;

    @Override
    protected void initView() {
        //得到本地json文本内容
        String fileName = "festival.json";
        String festivaljson = LocalJsonResolutionUtils.getJson(this, fileName);
        //转换为对象
        Festival festival = LocalJsonResolutionUtils.JsonToObject(festivaljson, Festival.class);
        FestivalGridViewAdapter adapter = new FestivalGridViewAdapter(this, festival.getList());
        mFestivalGridView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_festival;
    }

    @OnClick(R.id.festival_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
