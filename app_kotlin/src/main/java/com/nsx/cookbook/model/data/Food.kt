package com.nsx.cookbook.model.data

import com.google.gson.annotations.SerializedName

/**
 * 食谱检索 的返回数据类
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
    val classid: String,
    val name: String,
    val peoplenum: String,
    val preparetime: String,
    val cookingtime: String,
    val content: String,
    val pic: String,
    val tag: String,
    val material: List<Material>,
    val process: List<Proces>
)

data class Proces(
    val pcontent: String,
    val pic: String
)

data class Material(
    val mname: String,
    val type: String,
    val amount: String
)
