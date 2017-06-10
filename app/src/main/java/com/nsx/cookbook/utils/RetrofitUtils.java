package com.nsx.cookbook.utils;


import com.nsx.cookbook.app.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class RetrofitUtils {
    //懒汉式 直接构建对象， 不会存在同步问题
    private static RetrofitUtils instance = new RetrofitUtils();
    //Retrofit转Gson
    private Retrofit gsonRetrofit;
    //Retrofit转String
    private Retrofit stringRetrofit;

    private RetrofitUtils() {
        gsonRetrofit = new Retrofit.Builder()
                .baseUrl(Config.COOK_BASE_URL)
                //添加转换器，直接将内容转换成json 
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        stringRetrofit = new Retrofit.Builder()
                .baseUrl(Config.COOK_BASE_URL)
                //添加转换器，直接将内容转换成String
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public Retrofit getGsonRetrofit() {
        return gsonRetrofit;
    }

    public Retrofit getStringRetrofit() {
        return stringRetrofit;
    }

    public static RetrofitUtils getInstance() {
        return instance;
    }

}
