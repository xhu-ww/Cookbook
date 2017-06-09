package com.nsx.cookbook.ui.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nsx.cookbook.R;
import com.nsx.cookbook.adapter.FoodMaterialListAdapter;
import com.nsx.cookbook.adapter.StepListViewAdapter;
import com.nsx.cookbook.app.AppApplication;
import com.nsx.cookbook.app.Config;
import com.nsx.cookbook.base.BaseActivity;
import com.nsx.cookbook.bean.FoodDetailBean;
import com.nsx.cookbook.bean.FoodDetailBean.ResultBean.ListBean.MaterialBean;
import com.nsx.cookbook.bean.FoodDetailBean.ResultBean.ListBean.ProcessBean;
import com.nsx.cookbook.bean.custom.FoodCollection;
import com.nsx.cookbook.dao.FoodCollectDao;
import com.nsx.cookbook.model.CookbookModel;
import com.nsx.cookbook.utils.L;
import com.nsx.cookbook.utils.StringUtils;
import com.nsx.cookbook.widget.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by loptop on 2017/4/23.
 */

public class FoodDetailActivity extends BaseActivity {
    //菜谱相关网络请求
    CookbookModel cookbookModel;
    @BindView(R.id.fooddetail_image)
    ImageView mIvImage;
    @BindView(R.id.fooddetail_title)
    TextView mFooddetailTitle;
    @BindView(R.id.step_listview)
    NoScrollListView mFooddetailListview;
    @BindView(R.id.fooddetail_tv_describe)
    TextView mFooddetailTvDescribe;
    @BindView(R.id.fooddetail_tv_tags)
    TextView mFooddetailTvTags;
    @BindView(R.id.ingredients_listview)
    NoScrollListView mIngredientsListview;
    @BindView(R.id.burden_listview)
    NoScrollListView mBurdenListview;
    @BindView(R.id.fooddetail_fab)
    FloatingActionButton mFooddetailFab;
    //图片地址
    private String imageUrl;
    //暂时模拟 食谱是否收藏
    private boolean isCollect = false;
    private List<FoodCollection> collections;
    //食谱收藏 的数据库操作
    private FoodCollectDao foodCollectDao;

    private FoodCollection mCollection;
    private FoodDetailBean.ResultBean.ListBean mListBean;

    @Override
    protected void initView() {
        cookbookModel = CookbookModel.getInstance();
        mListBean = AppApplication.getInstance().getListBean();

        foodCollectDao = new FoodCollectDao(this);
        mCollection = new FoodCollection();
        //存储数据的集合
        collections = AppApplication.getInstance().getFoodCollection();
        L.e(TAG,"--------全局变量--"+ mListBean);
        for (FoodCollection collection : collections) {
            if (mListBean.getId().equals(collection.getId())) {
                isCollect = true;
            }
        }
        //如果已经搜藏
        if (isCollect) {
            mFooddetailFab.setImageResource(R.mipmap.ic_collection);
        } else {
            mFooddetailFab.setImageResource(R.mipmap.ic_collection_no);
        }
        setDataToView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fooddetail;
    }

    /**
     * 将数据设置到View上
     */
    private void setDataToView() {

        //图片地址
        imageUrl = mListBean.getPic();
        //设置标题名字
        String foodName = mListBean.getName();

        //设置用于收藏所需要的食物信息 数据库存储
        mCollection.setId(mListBean.getId());
        mCollection.setImage(imageUrl);
        mCollection.setName(foodName);

        //设置图片
        Glide.with(FoodDetailActivity.this)
                .load(imageUrl)
                .asBitmap()
                .centerCrop()
                .placeholder(R.color.colorAccent)
                .into(mIvImage);
        mFooddetailTitle.setText(foodName);
        //设置菜品描述内容
        mFooddetailTvDescribe.setText(StringUtils.removeOtherStr(mListBean.getContent()));
        //设置菜品标签
        mFooddetailTvTags.setText(mListBean.getTag());
        //设置菜品主材料明细内容
        //type 材料类型 0辅料 1主料
        List<MaterialBean> mainMaterial = getFoodMaterialData("1");
        FoodMaterialListAdapter materialAdapter = new FoodMaterialListAdapter(FoodDetailActivity.this, mainMaterial);
        mIngredientsListview.setAdapter(materialAdapter);
        //设置菜品辅材料明细内容
        List<MaterialBean> relishMaterial = getFoodMaterialData("0");
        materialAdapter = new FoodMaterialListAdapter(FoodDetailActivity.this, relishMaterial);
        mBurdenListview.setAdapter(materialAdapter);
        //设置菜品制作步骤
        List<ProcessBean> list = mListBean.getProcess();
        StepListViewAdapter adapter = new StepListViewAdapter(FoodDetailActivity.this, list);
        mFooddetailListview.setAdapter(adapter);

        //ListView设置子项点击事件
        mFooddetailListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FoodDetailActivity.this, StepActivity.class);
                //传递当前点击的步骤 位置
                intent.putExtra("POSITION", String.valueOf(position));
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.fooddetail_back, R.id.fooddetail_gohome, R.id.fooddetail_image, R.id.fooddetail_fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fooddetail_back:
                //返回上一页
                onBackPressed();
                break;
            case R.id.fooddetail_gohome:
                //跳转主界面
                startActivity(MainActivity.class);
                break;
            case R.id.fooddetail_image:
                //跳转显示图片的界面
                Intent intent = new Intent(FoodDetailActivity.this, ImageShowActivity.class);
                intent.putExtra("FOOD_IMAGE", imageUrl);
                startActivity(intent);
                break;
            //收藏按钮
            case R.id.fooddetail_fab:
                collectFood();
                break;
        }
    }

    /**
     * 得到材料的数据
     *
     * @param type 主料 还是辅料
     * @return
     */
    private List<MaterialBean> getFoodMaterialData(String type) {
        List<MaterialBean> list = new ArrayList<>();
        for (MaterialBean materialBean : mListBean.getMaterial()) {
            if (materialBean.getType().equals(type)) {
                list.add(materialBean);
            }
        }
        return list;
    }

    /**
     * 菜谱收藏的事件处理
     */
    private void collectFood() {
        //如果当前没收藏 则收藏，如收藏了，则删除数据库数据
        if (!isCollect) {
            showToast("收藏成功");
            //存入数据
            foodCollectDao.addFoodCollection(mCollection);
            Log.e(TAG,"--------------搜藏了" + mCollection.getId() + mCollection.getName());
            //同步全局共享变量
            AppApplication.getInstance().setFoodCollections(foodCollectDao.getFoodCollection());
            mFooddetailFab.setImageResource(R.mipmap.ic_collection);
        } else {
            //删除数据
            showToast("取消收藏");
            foodCollectDao.deleteFodCollection(mListBean.getId());
            //同步全局共享变量
            AppApplication.getInstance().setFoodCollections(foodCollectDao.getFoodCollection());
            mFooddetailFab.setImageResource(R.mipmap.ic_collection_no);
        }
        isCollect = !isCollect;
        //发送广播，通知数据已改变
        dataChange();
    }

    /**
     * 数据改变时的处理
     */
    private void dataChange() {
        //发送广播，通知数据已改变
        Intent intent = new Intent();
        intent.setAction(Config.BroadcastAction);
        sendBroadcast(intent);
        Log.e(TAG,"-------------------发送广播没？");
    }
}
