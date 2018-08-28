package com.nsx.cookbook.model

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.nsx.cookbook.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun okHttpClientBuilder(): OkHttpClient.Builder =
    OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .apply {
            if (BuildConfig.DEBUG)
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

fun retrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
    .client(okHttpClientBuilder().build())
    .addConverterFactory(
        GsonConverterFactory.create(
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        )
    )
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
