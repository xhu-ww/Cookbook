package com.nsx.cookbook.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nsx.cookbook.app.AppApplication;
import com.nsx.cookbook.utils.L;
import com.nsx.cookbook.utils.NetUtils;
import com.nsx.cookbook.utils.SimpleToast;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 日志输出标志
     */
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d(TAG, "------当前Activity-----onCreate()");
        //需要数据
        getIntentData();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
//        //设置沉浸式
//        if (setSteepStatusBar()) {
//            setStatusBar();
//        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //将Activity加入集合
        AppApplication.addActivity(this);
        initView();
        //注册广播
        registerNetworkChangeReceiver();
    }

    /**
     * 需要的数据
     */
    protected void getIntentData() {

    }

    /**
     * 初始化
     */
    protected abstract void initView();

    /**
     * 得到Activity布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 是否设置沉浸状态栏
     *
     * @return
     */
//    protected abstract boolean setSteepStatusBar();

    /**
     * 页面跳转
     *
     * @param clz 跳转到的Activity
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * 页面跳转，带动画效果
     *
     * @param clz
     * @param enter 进入动画
     * @param quit  退出动画
     */
    public void startActivity(Class<?> clz, int enter, int quit) {
        startActivity(clz);
        overridePendingTransition(enter, quit);
    }

    /**
     * 页面调转,可返回
     *
     * @param clz
     */
    public void startActivityForResult(Class<?> clz, int requestCode) {
        startActivityForResult(new Intent(BaseActivity.this, clz), requestCode);
    }

    /**
     * 携带数据的页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 带数据返回Activity
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 简化Toast
     *
     * @param msg
     */
    protected void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SimpleToast.show(getBaseContext(), msg);
            }
        });
    }

//    /**
//     * 设置沉浸状态栏
//     */
//    private void setStatusBar() {
//        //4.4 全透明状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//        //5.0 全透明实现
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        L.d(TAG, "-----------onDestroy()");
        //将当前Activity移除集合
        AppApplication.removeActivity(this);
        //注销广播
        unregisterReceiver(networkChangeReceiver);
    }

    NetworkChangeReceiver networkChangeReceiver;

    //注册网络变化广播接收器 
    private void registerNetworkChangeReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        //广播的动作类型  
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        //注册 
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    private class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent itent) {
            //判断有无网络  
            if (NetUtils.isConnected(BaseActivity.this)) {
//                Toast.makeText(context, "已连接网络", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(context, "已断开网络", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
