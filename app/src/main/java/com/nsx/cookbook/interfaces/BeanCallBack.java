package com.nsx.cookbook.interfaces;

/**
 * Created by Administrator on 2017/4/19.
 * 转换Bean的回调接口
 */

public interface BeanCallBack<T> {

    void onSucceed(T t);

    void onError(String msg);
}
