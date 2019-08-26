package com.dhh.gson.samples

import com.dhh.gson.ktx.GSON
import com.dhh.gson.ktx.toJson

/**
 * Created by dhh on 2019/8/26.
 *
 * @author dhh
 */
class GSONTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val xiaoMingJson = """{name:"XiaoMing",age:22}"""
            val xiaoLiJson = """{name:"XiaoLi",age:20}"""
            val classJson = """[$xiaoLiJson,$xiaoMingJson]"""
            val studentMapJson = """{xiaoMing:$xiaoMingJson,xiaoLi:$xiaoLiJson}"""
            val classMapJson = """{class1:$classJson,class2:$classJson}"""


            val xiaoMing = GSON.fromJson<Student>(xiaoMingJson)
            println(xiaoMing)
            val xiaoLi = GSON.fromJson<Student>(xiaoLiJson)
            println(xiaoLi)
            println(GSON.toJson(xiaoLi))
            val list = GSON.fromJson<List<Student>>(classJson)
            println(list)
            val studentMap = GSON.fromJson<Map<String, Student>>(studentMapJson)
            println(studentMap)
            println(studentMap["xiaoMing"]?.name)
            val map = GSON.fromJson<Map<String, List<Student>>>(classMapJson)
            println(map)
            println(map.toJson())


        }
    }

    data class Student(var name: String, var age: Int)


}