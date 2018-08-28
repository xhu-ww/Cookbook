package com.nsx.cookbook.model.service

import android.support.annotation.IntRange
import com.nsx.cookbook.app.COOK_APP_KEY
import com.nsx.cookbook.app.COOK_BASE_URL
import com.nsx.cookbook.app.DETAIL
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
}

class FoodService {
    private val api = retrofitBuilder()
        .baseUrl(COOK_BASE_URL)
        .build()
        .create(FoodApi::class.java)

    fun getFoodDetail(@IntRange(from = 0) foodId: Int): Single<FoodDetailBean> =
        api.foodDetail(foodId, COOK_APP_KEY)
}
