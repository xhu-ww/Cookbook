package com.nsx.cookbook.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.adapter.FoodListRecyclerAdapter;
import com.nsx.cookbook.base.BaseActivity;
import com.nsx.cookbook.bean.FoodDetailBean;
import com.nsx.cookbook.interfaces.BeanCallBack;
import com.nsx.cookbook.model.CookbookModel;
import com.nsx.cookbook.utils.DialogUtils;
import com.nsx.cookbook.utils.NetUtils;
import com.nsx.cookbook.widget.RecyclerRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;


public class FoodListActivity extends BaseActivity implements RecyclerRefreshLayout.SuperRefreshLayoutListener {

    @BindView(R.id.ll_food_list)
    LinearLayout mLinearLayout;
    @BindView(R.id.foodlist_title)
    TextView mFoodlistTitle;
    @BindView(R.id.foodlist_recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.recyclerrefreshlayout)
    RecyclerRefreshLayout mRefreshLayout;

    //菜谱相关网络请求
    CookbookModel cookbookModel;
    FoodListRecyclerAdapter adapter;

    private static int cid;
    private int start;
    @Override
    protected void initView() {
        cookbookModel = CookbookModel.getInstance();
        //得到主页界面传来的菜谱 菜系信息
        String[] cuisine = (String[]) getIntent().getCharSequenceArrayExtra("FOOD");
        //获得菜系名字
        String name = cuisine[0];
        //获得菜系ID
        cid = Integer.parseInt(cuisine[1]);
        //标题设置菜系名字
        mFoodlistTitle.setText(name);
        //设置可加载更多
        mRefreshLayout.setSuperRefreshLayoutListener(this);
        mRefreshLayout.setCanLoadMore(true);
        mRefreshLayout.setEnabled(false);
        //设置RecyclerView的模式
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //请求数据
        if (NetUtils.isConnected(this)) {
            requestData(cid, 0, 30);
        } else {
            mLinearLayout.setBackgroundResource(R.mipmap.no_net);
            showToast("网络未连接");
        }
    }

    /**
     * 请求数据
     *
     * @param classid
     * @param start
     * @param num
     */
    private void requestData(int classid, int start, int num) {
        //进度条
        DialogUtils.showProgressDialog(this);
        cookbookModel.cookBookById(classid, start, num, new BeanCallBack<FoodDetailBean>() {
            @Override
            public void onSucceed(final FoodDetailBean foodDetailBean) {
                adapter = new FoodListRecyclerAdapter(FoodListActivity.this,foodDetailBean.getResult().getList());
                //设置适配器
                mRecyclerview.setAdapter(adapter);
                DialogUtils.dismiss();
                //设置RecyclerView子Item的点击事件
                adapter.setOnItemClickListener(new FoodListRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick() {
                        startActivity(FoodDetailActivity.class);
                    }
                });
            }

            @Override
            public void onError(String msg) {
                showToast(msg);
                DialogUtils.dismiss();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_foodlist;
    }


    @OnClick(R.id.foodlist_back)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onRefreshing() {

    }

    @Override
    public void onLoadMore() {
        if (NetUtils.isConnected(this)) {
            //进度条
            DialogUtils.showProgressDialog(this);
            start = start + 30;
            cookbookModel.cookBookById(cid, start, 30, new BeanCallBack<FoodDetailBean>() {
                @Override
                public void onSucceed(FoodDetailBean foodDetailBean) {
                    adapter.addMoreItem(foodDetailBean.getResult().getList());
                    mRefreshLayout.onComplete();
                    DialogUtils.dismiss();
                }

                @Override
                public void onError(String msg) {
                    showToast(msg);
                    DialogUtils.dismiss();
                }
            });
        } else {
            showToast("网络未连接");
        }
    }
}
