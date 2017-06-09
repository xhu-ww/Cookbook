package com.nsx.cookbook.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nsx.cookbook.R;
import com.nsx.cookbook.adapter.FoodLoveGridViewAdapter;
import com.nsx.cookbook.app.AppApplication;
import com.nsx.cookbook.app.Config;
import com.nsx.cookbook.base.BaseFragment;
import com.nsx.cookbook.bean.FoodDetailBean;
import com.nsx.cookbook.bean.custom.FoodCollection;
import com.nsx.cookbook.dao.FoodCollectDao;
import com.nsx.cookbook.interfaces.BeanCallBack;
import com.nsx.cookbook.model.CookbookModel;
import com.nsx.cookbook.ui.activity.FoodDetailActivity;
import com.nsx.cookbook.utils.DialogUtils;
import com.nsx.cookbook.utils.L;
import com.nsx.cookbook.widget.NonScrollGridView;

import java.util.List;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/4/17.
 */

public class LoveFragment extends BaseFragment {

    @BindView(R.id.love_gridview)
    NonScrollGridView mLoveGridview;
    //没有数据时显示 "您还没有搜藏哦"
    @BindView(R.id.tv_show_none)
    TextView mTextView;
    FoodLoveGridViewAdapter adapter;
    CookbookModel mCookbookModel;

    //食谱收藏 的数据库操作
    private FoodCollectDao foodCollectDao;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_love;
    }

    @Override
    public void initview() {
        foodCollectDao = new FoodCollectDao(mActivity);
        List<FoodCollection> list = AppApplication.getInstance().getFoodCollection();
        L.e("--------------------" + list);
        if (list.isEmpty()) {
            mTextView.setVisibility(View.VISIBLE);
        } else {
            adapter = new FoodLoveGridViewAdapter(mActivity, list);
            mLoveGridview.setAdapter(adapter);
            //设置GridView子Item的点击事件
            adapter.setOnItemClickListener(new FoodLoveGridViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(String foodId) {
                    //根据 ID请求数据,并跳转界面
                    int id = Integer.parseInt(foodId);
                    DialogUtils.showProgressDialog(mActivity);
                    requestFoodData(id);
                }
            });
        }
        //注册广播接收者
        IntentFilter filter = new IntentFilter();
        filter.addAction(Config.BroadcastAction);
        mActivity.registerReceiver(mReceiver, filter);
    }

    /**
     * 广播接收者
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Config.BroadcastAction)) {
                //一旦数据改变 变 改变适配器中的数据
                List<FoodCollection> list = AppApplication.getInstance().getFoodCollection();
                adapter.setList(list);
            }
        }
    };

    private void requestFoodData(int id) {
        mCookbookModel = CookbookModel.getInstance();
        mCookbookModel.cookBookDetail(id, new BeanCallBack<FoodDetailBean.ResultBean.ListBean>() {
            @Override
            public void onSucceed(FoodDetailBean.ResultBean.ListBean listBean) {
                //先清空数据
//                AppApplication.getInstance().setListBean(null);
                //在设置新的数据，将请求到的数据 放入全局共享数据
                AppApplication.getInstance().setListBean(listBean);
                Log.e(TAG, "--------------请求了" + listBean.getId() + listBean.getName());
                Intent intent = new Intent(mActivity, FoodDetailActivity.class);
                DialogUtils.dismiss();
                startActivity(intent);
            }

            @Override
            public void onError(String msg) {
                showToast(msg);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //注销广播
        mActivity.unregisterReceiver(mReceiver);
    }
}
