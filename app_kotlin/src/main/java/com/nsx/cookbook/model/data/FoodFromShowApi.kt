package com.nsx.cookbook.model.data


data class FoodFromShowApi(
    val showapi_res_error: String,
    val showapi_res_id: String,
    val showapi_res_code: Int,
    val showapi_res_body: ShowapiResBody
)

data class ShowapiResBody(
    val allNum: Int,
    val remark: String,
    val allPage: Int,
    val msg: String,
    val flag: Boolean,
    val datas: List<Data>,
    val page: Int,
    val ret_code: String
)

data class Data(
    val des: String,
    val steps: List<Step>,
    val id: String,
    val largeImg: String,
    val smallImg: String,
    val type_v3: String,
    val cpName: String,
    val type_v2: String,
    val tip: String,
    val yl: List<Yl>,
    val type_v1: String,
    val type: String
)

data class Yl(
    val ylName: String,
    val ylUnit: String
)

data class Step(
    val imgUrl: String,
    val orderNum: Int,
    val content: String
)
