package com.nsx.cookbook.service;

import com.nsx.cookbook.app.Config;
import com.nsx.cookbook.bean.TodayHistory;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 历史上的今天 的网络请求
 */

public interface ITodayHistoryService {

    @POST(Config.HISTORY_QUERY)
    @FormUrlEncoded
    Call<TodayHistory> getTodayHistory(@Field("appkey") String appkey
            , @Field("month") String month,@Field("day") String day);
}
