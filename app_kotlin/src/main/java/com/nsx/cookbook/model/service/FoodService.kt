package com.nsx.cookbook.model.service

import android.support.annotation.IntRange
import com.nsx.cookbook.app.*
import com.nsx.cookbook.model.data.FoodDetailBean
import com.nsx.cookbook.model.retrofitBuilder
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

private interface FoodApi {

    //按食物ID搜索菜谱
    @POST(DETAIL)
    fun foodDetail(
        @Query("id") id: Int,
        @Query("appkey") appKey: String
    ): Single<FoodDetailBean>

    @POST(BYCLASSID)
    fun queryFoodsById(
        @Query("classid") classid: Int,
        @Query("start") start: Int,
        @Query("num") num: Int,
        @Query("appkey") appkey: String
    ): Single<FoodDetailBean>
}

class FoodService {
    private val api = retrofitBuilder()
        .baseUrl(COOK_BASE_URL)
        .build()
        .create(FoodApi::class.java)

    fun getFoodDetail(@IntRange(from = 0) foodId: Int): Single<FoodDetailBean> =
        api.foodDetail(foodId, COOK_APP_KEY)

    fun getFoods(classId: Int, @IntRange(from = 0) page: Int = 0) =
        api.queryFoodsById(classId, page, PAGE_LIMIT, COOK_APP_KEY)
}
