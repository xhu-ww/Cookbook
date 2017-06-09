package com.nsx.cookbook.interfaces;

import com.nsx.cookbook.bean.CalendarBean;
import com.nsx.cookbook.bean.LaunarCalendar;

/**
 * Created by loptop on 2017/5/1.
 */

public interface ICalendarModel {
    /**
     * 日历查询
     */
    public void getCalendar(String date, BeanCallBack<CalendarBean> callback);
    public void getLaunarCalendar(String year, String month, String day,BeanCallBack<LaunarCalendar> callback);
}
