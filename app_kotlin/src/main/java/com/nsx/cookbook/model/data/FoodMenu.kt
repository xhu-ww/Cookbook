package com.nsx.cookbook.model.data

data class FoodParentMenu(
    val group: String,
    val groupId: Int,
    val list: List<FoodChildMenu>
)

data class FoodChildMenu(
    val childGroup: String,
    val childList: List<String>
)