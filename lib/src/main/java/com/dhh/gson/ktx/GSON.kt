package com.dhh.gson.ktx

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken

object GSON {
    /**
     * 初始化默认的 [com.google.gson.Gson] 转换器
     */
    var gson = GsonBuilder().setLenient()
        .create()

    inline fun <reified T> fromJson(json: String): T {
        val type = object : TypeToken<T>() {}.type
        return gson.fromJson(json, type)
    }

    inline fun <reified T> fromJson(jsonElement: JsonElement): T {
        val type = object : TypeToken<T>() {}.type
        return gson.fromJson(jsonElement.toString(), type)
    }

    fun toJson(any: Any) = gson.toJson(any)


    fun toJson(jsonElement: JsonElement) = gson.toJson(jsonElement)

}
