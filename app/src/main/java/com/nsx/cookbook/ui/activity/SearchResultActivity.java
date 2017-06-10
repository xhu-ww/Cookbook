package com.nsx.cookbook.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nsx.cookbook.R;
import com.nsx.cookbook.adapter.FoodListRecyclerAdapter;
import com.nsx.cookbook.app.Config;
import com.nsx.cookbook.base.BaseActivity;
import com.nsx.cookbook.bean.FoodDetailBean;
import com.nsx.cookbook.interfaces.BeanCallBack;
import com.nsx.cookbook.model.CookbookModel;
import com.nsx.cookbook.utils.DialogUtils;
import com.nsx.cookbook.utils.FileUtil;
import com.nsx.cookbook.utils.NetUtils;
import com.nsx.cookbook.widget.RecyclerRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;


public class SearchResultActivity extends BaseActivity implements TextWatcher, RecyclerRefreshLayout.SuperRefreshLayoutListener {
    @BindView(R.id.et_search_result)
    EditText mEtSearchResult;
    @BindView(R.id.search_result_clear)
    ImageView mSearchResultClear;
    @BindView(R.id.search_result_recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.search_result_refresh)
    RecyclerRefreshLayout mRefreshLayout;
    @BindView(R.id.ll_search_result)
    LinearLayout mLinearLayout;
    //搜索的内容
    private String keyword;
    //菜谱相关网络请求
    CookbookModel cookbookModel;
    FoodListRecyclerAdapter adapter;

    @Override
    protected void initView() {
        cookbookModel = CookbookModel.getInstance();
        //得到数据
        String searchContent = getIntent().getStringExtra("SEARCH");
        mEtSearchResult.setText(searchContent);
        //输入框设置文本改变事件监听
        mEtSearchResult.addTextChangedListener(this);
        //设置RecyclerView的模式
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //设置可加载更多
        mRefreshLayout.setSuperRefreshLayoutListener(this);
        mRefreshLayout.setCanLoadMore(true);
        mRefreshLayout.setEnabled(false);
        //搜索菜谱
        if (NetUtils.isConnected(this)) {
            requestData(searchContent, 30);
        } else {
            mLinearLayout.setBackgroundResource(R.mipmap.no_net);
            showToast("网络未连接");
        }
        //刚进界面时，文字消除按钮 是不显示的
        mSearchResultClear.setVisibility(View.INVISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_result;
    }

    @OnClick({R.id.search_result_back, R.id.search_result_button, R.id.search_result_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_result_back:
                //返回
                onBackPressed();
                break;
            case R.id.search_result_button:
                //搜索菜谱
                keyword = mEtSearchResult.getText().toString();
                requestDataAgain(keyword, 30);
                //文件保存在 /data/data/PACKAGE_NAME/files 目录下 并以 #' 分割
                FileUtil.write(this, Config.SEARCHFILENAME, keyword + "#'");
                break;
            case R.id.search_result_clear:
                //清空当前文本
                mEtSearchResult.getEditableText().clear();
                break;
        }
    }

    /**
     * 请求数据
     */
    private void requestData(String keyword, int num) {
        DialogUtils.showProgressDialog(this);
        cookbookModel.cookBookSearch(keyword, num, new BeanCallBack<FoodDetailBean>() {
            @Override
            public void onSucceed(final FoodDetailBean foodDetailBean) {
                //设置适配器
                adapter = new FoodListRecyclerAdapter(SearchResultActivity.this,
                        foodDetailBean.getResult().getList());
                mRecyclerview.setAdapter(adapter);
                DialogUtils.dismiss();
                //设置RecyclerView子Item的点击事件
                adapter.setOnItemClickListener(new FoodListRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick() {
                        //全局共享数据
                        startActivity(FoodDetailActivity.class);
                    }
                });
            }

            @Override
            public void onError(String msg) {
                DialogUtils.dismiss();
            }
        });
    }

    /**
     * 再次请求数据
     *
     * @param keyword
     * @param num
     */
    private void requestDataAgain(String keyword, int num) {
        DialogUtils.showProgressDialog(this);
        cookbookModel.cookBookSearch(keyword, num, new BeanCallBack<FoodDetailBean>() {
            @Override
            public void onSucceed(FoodDetailBean foodDetailBean) {
                adapter.setList(foodDetailBean.getResult().getList());
                DialogUtils.dismiss();
            }

            @Override
            public void onError(String msg) {
                DialogUtils.dismiss();
            }
        });
    }

    //输入文本之前调用
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    //输入文本时调用
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //文本长度大于0 则显示清除文本的图片
        if (s.length() > 0) {
            mSearchResultClear.setVisibility(View.VISIBLE);
        } else {
            mSearchResultClear.setVisibility(View.INVISIBLE);
        }
    }

    //输入文本之后调用
    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onRefreshing() {

    }

    @Override
    public void onLoadMore() {
        DialogUtils.showProgressDialog(this);
        cookbookModel.cookBookSearch(keyword, 30, new BeanCallBack<FoodDetailBean>() {
            @Override
            public void onSucceed(FoodDetailBean foodDetailBean) {
                adapter.addMoreItem(foodDetailBean.getResult().getList());
                DialogUtils.dismiss();
            }

            @Override
            public void onError(String msg) {
                DialogUtils.dismiss();
            }
        });
    }
}
