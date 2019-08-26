@file:JvmName("_GSONKTX")

package com.dhh.gson.ktx

import com.google.gson.JsonElement

/**
 * Created by dhh on 2019/8/26.
 *
 * @author dhh
 */
inline fun <reified T> String.toBean() = GSON.fromJson<T>(this)

inline fun <reified T> JsonElement.toBean() = GSON.fromJson<T>(this)

fun Any.toJson() = GSON.toJson(this)

fun JsonElement.toJson() = GSON.toJson(this)