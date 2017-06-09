package com.nsx.cookbook.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.adapter.FoodCategoryListViewAdapter;
import com.nsx.cookbook.adapter.GridViewAdapter;
import com.nsx.cookbook.base.BaseFragment;
import com.nsx.cookbook.bean.FoodCategoryBean;
import com.nsx.cookbook.model.LocalJsonResolutionUtils;
import com.nsx.cookbook.ui.activity.SearchActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/4/17.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.foodcategory_lv_left)
    ListView mListView;
    @BindView(R.id.foodcategory_gv_right)
    GridView mGridView;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initview() {
        getCookBookData();
    }

    /**
     * 请求网络得到数据
     */
    private void getCookBookData() {

        //得到本地json文本内容
        String fileName = "food.json";
        String foodJson = LocalJsonResolutionUtils.getJson(mActivity, fileName);
        //转换为对象
        final FoodCategoryBean foodCategoryBean = LocalJsonResolutionUtils.JsonToObject(foodJson, FoodCategoryBean.class);
        //ListView适配器
        final FoodCategoryListViewAdapter listViewAdapter = new FoodCategoryListViewAdapter(mActivity, foodCategoryBean.getResult());
        mListView.setAdapter(listViewAdapter);
        //GridView适配器 默认加载第一个
        final GridViewAdapter gridViewAdapter = new GridViewAdapter(mActivity, foodCategoryBean.getResult().get(0).getList());
        mGridView.setAdapter(gridViewAdapter);
        //ListView item点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //改变GridView中需要显示的数据
                gridViewAdapter.setList(foodCategoryBean.getResult().get(position).getList());
                //改变CurrentItem值，用于改变 字体颜色
                listViewAdapter.setCurrentItem(position);
                //通知改变
                listViewAdapter.notifyDataSetChanged();
            }
        });
    }


    @OnClick(R.id.main_food_search)
    public void onViewClicked() {
        //跳转搜索页面
        startActivity(new Intent(mActivity, SearchActivity.class));
    }
}
