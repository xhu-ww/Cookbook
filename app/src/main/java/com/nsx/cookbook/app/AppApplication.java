package com.nsx.cookbook.app;

import android.app.Activity;
import android.app.Application;

import com.nsx.cookbook.bean.FoodDetailBean;
import com.nsx.cookbook.bean.custom.FoodCollection;
import com.nsx.cookbook.bean.custom.LocalCalendar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 * 共享数据
 * 第三方SDK的初始化
 * 循环遍历退出
 */

public class AppApplication extends Application {

    private static AppApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化第三方SDK
        //MOB短信SDK初始化

    }

    /**
     * 得到AppApplication对象
     *
     * @return
     */
    public static AppApplication getInstance() {
        return instance;
    }

    /**
     * 存储Activity的集合
     */
    private static List<Activity> activities = new ArrayList<>();

    /**
     * 添加Activity进集合
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 将Activity移除集合
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 销毁所有的Actiityv
     */
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /****************************************全局共享的数据*****************************************/
    //自定义的日历对象
    private LocalCalendar mLocalCalendar;

    public LocalCalendar getLocalDate() {
        return mLocalCalendar;
    }

    public void setLocalDate(LocalCalendar localDate) {
        mLocalCalendar = localDate;
    }

    //食谱收藏 集合
    private List<FoodCollection> foodCollection;

    public List<FoodCollection> getFoodCollection() {
        return foodCollection;
    }

    public void setFoodCollections(List<FoodCollection> foodCollection) {
        this.foodCollection = foodCollection;
    }
    //一份食物的详细介绍
    private FoodDetailBean.ResultBean.ListBean mListBean;

    public FoodDetailBean.ResultBean.ListBean getListBean() {
        return mListBean;
    }

    public void setListBean(FoodDetailBean.ResultBean.ListBean listBean) {
        mListBean = listBean;
    }
}
