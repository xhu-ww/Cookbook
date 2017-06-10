package com.nsx.cookbook.model;

import com.nsx.cookbook.app.Config;
import com.nsx.cookbook.base.ResponseCallBack;
import com.nsx.cookbook.bean.TodayHistory;
import com.nsx.cookbook.interfaces.BeanCallBack;
import com.nsx.cookbook.interfaces.ITodayHistoryModel;
import com.nsx.cookbook.service.ITodayHistoryService;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TodayHistoryModel implements ITodayHistoryModel{
    private ITodayHistoryService iTodayHistoryService;

    private static TodayHistoryModel instance = new TodayHistoryModel();

    public static TodayHistoryModel getInstance() {
        return instance;
    }

    private TodayHistoryModel() {
        //得到Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.HISTORY_BASE_URL)
                //添加转换器，直接将内容转换成json 
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建请求接口
        iTodayHistoryService = retrofit.create(ITodayHistoryService.class);
    }
    @Override
    public void getTodayHistory(String month, String day, final BeanCallBack<TodayHistory> callback) {
        Call<TodayHistory> call = iTodayHistoryService.getTodayHistory(Config.COOK_APP_KEY,month,day);
        call.enqueue(new ResponseCallBack<TodayHistory>() {
            @Override
            public void onResponse(Call<TodayHistory> call, Response<TodayHistory> response) {

                if (response.body().getMsg().equals("ok")) {
                    callback.onSucceed(response.body());
                } else {
                    callback.onError("数据请求成功，但数据错误");
                }
            }
        });
    }
}
