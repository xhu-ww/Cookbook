package com.nsx.cookbook.model.data

import com.google.gson.annotations.SerializedName

/**
 * 食谱检索 以及食谱按分类搜索 返回数据类
 */
data class FoodSearchBean(
    val status: String,
    val msg: String,
    val result: Result
)

data class Result(
    val num: String,
    @SerializedName("list")
    val foods: List<Food>
)

/**
 * 食谱详情 的返回数据类
 */
data class FoodDetailBean(
    val status: String,
    val msg: String,
    @SerializedName("result")
    val food: Food
)

/**
 * 通用数据类 食谱详情
 */
data class Food(
    val id: String,
    @SerializedName("classid")
    val classId: String,
    val name: String,
    @SerializedName("peoplenum")
    val peopleNum: String,
    @SerializedName("preparetime")
    val prepareTime: String,
    @SerializedName("cookingtime")
    val cookingTime: String,
    val content: String,
    val pic: String,
    val tag: String,
    val material: List<Material>,
    val process: List<Process>
)

data class Process(
    @SerializedName("pcontent")
    val processContent: String,
    val pic: String
)

data class Material(
    @SerializedName("mname")
    val name: String,
    val type: String,
    val amount: String
)
