package com.nsx.cookbook.ui.food.viewModel

import android.content.res.AssetManager
import android.databinding.ObservableField
import com.nsx.cookbook.base.BaseViewModel
import com.nsx.cookbook.model.data.FoodParentMenu
import com.nsx.cookbook.utils.jsonToObject
import com.nsx.cookbook.utils.readJsonFromAsset

class FoodMenuViewModel(assetManager: AssetManager) : BaseViewModel() {

    val foodMenu = ObservableField<FoodParentMenu>()

    init {
        val jsonString = assetManager.readJsonFromAsset("food.json")
        foodMenu.set(jsonToObject(jsonString, FoodParentMenu::class.java))
    }
}
