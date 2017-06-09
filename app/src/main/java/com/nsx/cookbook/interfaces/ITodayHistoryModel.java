package com.nsx.cookbook.interfaces;

import com.nsx.cookbook.bean.LaunarCalendar;
import com.nsx.cookbook.bean.TodayHistory;

/**
 * Created by Administrator on 2017/5/6.
 */

public interface ITodayHistoryModel {
    /**
     * 历史上的今天
     *
     */
    public void getTodayHistory(String month, String day,BeanCallBack<TodayHistory> callback);
}
