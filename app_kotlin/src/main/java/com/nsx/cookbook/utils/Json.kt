package com.nsx.cookbook.utils

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayList

fun AssetManager.readJsonFromAsset(fileName: String): String {
    val reader = BufferedReader(InputStreamReader(open(fileName), "utf-8"))
    val jsonStrs = reader.readLines()
    return jsonStrs.joinToString("")
}

fun <T> jsonToObject(json: String, clazz: Class<T>): T? {
    try {
        return Gson().fromJson(json, clazz)
    } catch (e: JsonSyntaxException) {
        e.printStackTrace()
    }
    return null
}

fun <T> jsonToList(json: String, clazz: Class<T>): List<T> {
    val type = object : TypeToken<ArrayList<JsonObject>>() {}.type
    val jsonObjects = Gson().fromJson<ArrayList<JsonObject>>(json, type)

    val list = ArrayList<T>()
    jsonObjects.forEach {
        try {
            list.add(Gson().fromJson(it, clazz))
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
        }
    }
    return list
}
