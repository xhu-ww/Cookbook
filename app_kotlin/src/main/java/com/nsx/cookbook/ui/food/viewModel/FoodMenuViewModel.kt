package com.nsx.cookbook.ui.food.viewModel

import android.content.res.AssetManager
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.nsx.cookbook.base.BaseViewModel
import com.nsx.cookbook.model.data.FoodParentMenu
import com.nsx.cookbook.utils.databinding.setAll
import com.nsx.cookbook.utils.jsonToList
import com.nsx.cookbook.utils.jsonToObject
import com.nsx.cookbook.utils.readJsonFromAsset

class FoodMenuViewModel(assetManager: AssetManager) : BaseViewModel() {

    val foodMenu = ObservableArrayList<FoodParentMenu>()

    init {
        val jsonString = assetManager.readJsonFromAsset("food.json")
        foodMenu.setAll(jsonToList(jsonString, FoodParentMenu::class.java))
    }
}
