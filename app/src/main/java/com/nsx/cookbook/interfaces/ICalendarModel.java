package com.nsx.cookbook.interfaces;

import com.nsx.cookbook.bean.CalendarBean;
import com.nsx.cookbook.bean.LaunarCalendar;


public interface ICalendarModel {
    /**
     * 日历查询
     */
    public void getCalendar(String date, BeanCallBack<CalendarBean> callback);
    public void getLaunarCalendar(String year, String month, String day,BeanCallBack<LaunarCalendar> callback);
}
