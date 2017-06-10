package com.nsx.cookbook.service;

import com.nsx.cookbook.app.Config;
import com.nsx.cookbook.bean.CalendarBean;
import com.nsx.cookbook.bean.LaunarCalendar;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ICalendarService {
    //日历查詢
    @POST(Config.CALENDAR)
    @FormUrlEncoded
    Call<CalendarBean> getCalendar(@Field("showapi_appid") String showapi_appid,
                                   @Field("showapi_sign") String showapi_sign,
                                   @Field("date") String date);
    //农历查询
    @POST(Config.LAUNAR_DATE)
    @FormUrlEncoded
    Call<LaunarCalendar> getLaunarCalendar(@Field("appkey") String appkey,
                                           @Field("year") String year ,
                                           @Field("month") String month,
                                           @Field("day") String day);

}
