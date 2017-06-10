package com.nsx.cookbook.interfaces;

import com.nsx.cookbook.bean.TodayHistory;


public interface ITodayHistoryModel {
    /**
     * 历史上的今天
     *
     */
    public void getTodayHistory(String month, String day,BeanCallBack<TodayHistory> callback);
}
