package com.nsx.cookbook.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nsx.cookbook.utils.SimpleToast;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {
    /**
     * 日志输出标志
     */
    protected final String TAG = this.getClass().getSimpleName();
    Unbinder unbinder;
    /**
     * 贴附的activity
     */
    protected FragmentActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (FragmentActivity) context;
        //注册网络变化广播
//        registerNetworkChangeReceiver();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(this, layout);
        initview();
        return layout;
    }

    public abstract int getLayoutId();

    public abstract void initview();

    protected void showToast(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SimpleToast.show(getActivity(), msg);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //注销广播
//        mActivity.unregisterReceiver(networkChangeReceiver);
    }

//    NetworkChangeReceiver networkChangeReceiver;
//
//    //注册网络变化广播接收器 
//    private void registerNetworkChangeReceiver() {
//        IntentFilter intentFilter = new IntentFilter();
//        //广播的动作类型  
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        networkChangeReceiver = new NetworkChangeReceiver();
//        //注册 
//        mActivity.registerReceiver(networkChangeReceiver, intentFilter);
//    }
//
//    private class NetworkChangeReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent itent) {
//            //判断有无网络  
//            if (NetUtils.isConnected(mActivity)) {
//                L.e(TAG, "------------------" + NetUtils.isConnected(mActivity));
//                requestData();
//                Toast.makeText(context, "已连接网络", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(context, "已断开网络", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    protected void requestData() {
//    }
}
