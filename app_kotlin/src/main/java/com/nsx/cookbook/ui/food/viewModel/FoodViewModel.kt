package com.nsx.cookbook.ui.food.viewModel

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.support.annotation.IntRange
import com.nsx.cookbook.base.BaseViewModel
import com.nsx.cookbook.model.data.Food
import com.nsx.cookbook.model.service.FoodService
import com.nsx.cookbook.utils.databinding.setAll
import com.nsx.cookbook.utils.rx.async
import com.nsx.cookbook.utils.rx.status
import io.reactivex.rxkotlin.subscribeBy

class FoodViewModel(private val foodService: FoodService) : BaseViewModel() {

    val food = ObservableField<Food>()
    val foods = ObservableArrayList<Food>()

    val processing = ObservableBoolean()
    val noData = ObservableBoolean()
    val noMore = ObservableBoolean()

    private var page = 0

    fun requestFoodDetail(@IntRange(from = 0) foodId: Int) {
        if (processing.get()) return

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

    fun refreshFoods(classId: Int) {
        if (processing.get()) return

        foodService.getFoods(classId)
            .map { it.result }
            .map { it.foods }
            .filter { it.isNotEmpty() }
            .async()
            .status(processing)
            .doOnSuccess { page = 0 }
            .subscribeBy(
                onSuccess = { it -> foods.setAll(it) },
                onError = { it.printStackTrace() }
            )
            .bind()
    }

    fun loadMoreFoods(classId: Int) {
        if (processing.get()) return

        page++
        foodService.getFoods(classId, page)
            .map { it.result }
            .map { it.foods }
            .async()
            .status(processing)
            .doOnError { page-- }
            .subscribeBy(
                onSuccess = { it -> foods.addAll(it) },
                onError = { it.printStackTrace() }
            )
            .bind()
    }
}
