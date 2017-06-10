package com.nsx.cookbook.model;

import com.nsx.cookbook.app.Config;
import com.nsx.cookbook.base.ResponseCallBack;
import com.nsx.cookbook.bean.CalendarBean;
import com.nsx.cookbook.bean.LaunarCalendar;
import com.nsx.cookbook.interfaces.BeanCallBack;
import com.nsx.cookbook.interfaces.ICalendarModel;
import com.nsx.cookbook.service.ICalendarService;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 因为 日历 和 菜谱以及笑话、微信大全的接口来源又是一个公司的
 */

public class CalendarModel implements ICalendarModel {
    private ICalendarService calendarService;

    private static CalendarModel instance = new CalendarModel();

    public static CalendarModel getInstance() {
        return instance;
    }

    private CalendarModel() {
    }
    //日历
    @Override
    public void getCalendar(String date, final BeanCallBack<CalendarBean> callback) {
        //得到Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.CALENDAR_BASE_URL)
                //添加转换器，直接将内容转换成json 
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建请求接口
        calendarService = retrofit.create(ICalendarService.class);

        Call<CalendarBean> call = calendarService.getCalendar(Config.SHOWAPI_APPID, Config.SHOWAPI_SIGN, date);
        call.enqueue(new ResponseCallBack<CalendarBean>() {
            @Override
            public void onResponse(Call<CalendarBean> call, Response<CalendarBean> response) {
                if (response.body().getShowapi_res_code() == 0) {
                    callback.onSucceed(response.body());
                } else {
                    callback.onError("日历数据请求成功，但数据错误");
                }
            }
        });
    }
    //农历
    @Override
    public void getLaunarCalendar(String year, String month, String day, final BeanCallBack<LaunarCalendar> callback) {
        //得到Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.LAUNAR_BASE_URL)
                //添加转换器，直接将内容转换成json 
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建请求接口
        calendarService = retrofit.create(ICalendarService.class);
        Call<LaunarCalendar> call = calendarService.getLaunarCalendar(Config.COOK_APP_KEY,year,month,day);
        call.enqueue(new ResponseCallBack<LaunarCalendar>() {
            @Override
            public void onResponse(Call<LaunarCalendar> call, Response<LaunarCalendar> response) {
                if (response.body().getMsg().equals("ok")) {
                    callback.onSucceed(response.body());
                } else {
                    callback.onError("日历数据请求成功，但数据错误");
                }
            }
        });
    }
}
