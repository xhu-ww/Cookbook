package com.nsx.cookbook.ui.food

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.support.annotation.IntRange
import com.nsx.cookbook.base.BaseViewModel
import com.nsx.cookbook.model.data.Food
import com.nsx.cookbook.model.service.FoodService
import com.nsx.cookbook.utils.rx.async
import com.nsx.cookbook.utils.rx.status
import io.reactivex.rxkotlin.subscribeBy

class FoodViewModel(private val foodService: FoodService) : BaseViewModel() {

    val processing = ObservableBoolean()

    val food = ObservableField<Food>()

//    init {
//        requestFoodDetail(5)
//    }

    fun requestFoodDetail(@IntRange(from = 0) foodId: Int) {
        foodService.getFoodDetail(foodId)
            .map { it.food }
            .async()
            .status(processing)
            .subscribeBy(
                onSuccess = food::set,
                onError = { it.printStackTrace() }
            )
            .bind()
    }
}

