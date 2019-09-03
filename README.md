# GSON-KTS

### A set of Kotlin extensions for Gson.
### 一套 Gson 的 Kotlin 扩展库

### version: [ ![Download](https://api.bintray.com/packages/dhhandroid/maven/gson-ktx/images/download.svg) ](https://bintray.com/dhhandroid/maven/gson-ktx/_latestVersion)

### gradle:
```groovy
implementation 'com.dhh:gson-ktx:version'
```

### maven:
```xml
<dependency>
  <groupId>com.dhh</groupId>
  <artifactId>gson-ktx</artifactId>
  <version>version</version>
  <type>pom</type>
</dependency>
```

## 使用方法如下( [Sample](/samples/src/main/java/com/dhh/gson/samples/GSONTest.kt) )
### 通过 GSON 类访问相关方法： 
```kotlin
class GSONTest {

    data class Student(var name: String, var age: Int)

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val xiaoMingJson = """{name:"XiaoMing",age:22}"""
            val xiaoLiJson = """{name:"XiaoLi",age:20}"""
            val classJson = """[$xiaoLiJson,$xiaoMingJson]"""
            val studentMapJson = """{xiaoMing:$xiaoMingJson,xiaoLi:$xiaoLiJson}"""
            val classMapJson = """{class1:$classJson,class2:$classJson}"""


            val xiaoMing: Student = GSON.fromJson<Student>(xiaoMingJson)
            println(xiaoMing)//print -> Student(name=XiaoMing, age=22)

            val xiaoLi: Student = GSON.fromJson<Student>(xiaoLiJson)
            println(xiaoLi)//print -> Student(name=XiaoLi, age=20)
            println(GSON.toJson(xiaoLi))//print -> {"name":"XiaoLi","age":20}

            val list: List<Student> = GSON.fromJson<List<Student>>(classJson)
            println(list)//print -> [Student(name=XiaoLi, age=20), Student(name=XiaoMing, age=22)]

            val studentMap: Map<String, Student> =
                GSON.fromJson<Map<String, Student>>(studentMapJson)
            println(studentMap)//print -> {xiaoMing=Student(name=XiaoMing, age=22), xiaoLi=Student(name=XiaoLi, age=20)}
            println(studentMap["xiaoMing"]?.name)//print -> XiaoMing

            val map: Map<String, List<Student>> = GSON.fromJson<Map<String, List<Student>>>(classMapJson)
            println(map) //print -> {class1=[Student(name=XiaoLi, age=20), Student(name=XiaoMing, age=22)], class2=[Student(name=XiaoLi, age=20), Student(name=XiaoMing, age=22)]}
            
            println(map.toJson())//print -> {"class1":[{"name":"XiaoLi","age":20},{"name":"XiaoMing","age":22}],"class2":[{"name":"XiaoLi","age":20},{"name":"XiaoMing","age":22}]}


        }
    }
}
```
### 由于 Kotlin 的类型自动推断功能，可以简写如下：
```kotlin
    val xiaoMing: Student = GSON.fromJson(xiaoMingJson)
    
    val list: List<Student> = GSON.fromJson(classJson)
    
    val studentMap: Map<String, Student> = GSON.fromJson(studentMapJson)
    
    val map: Map<String, List<Student>> = GSON.fromJson(classMapJson)
```
### 或者：
```kotlin
    val xiaoMing = GSON.fromJson<Student>(xiaoMingJson)
    
    val list = GSON.fromJson<List<Student>>(classJson)
    
    val studentMap = GSON.fromJson<Map<String, Student>>(studentMapJson)
    
    val map = GSON.fromJson<Map<String, List<Student>>>(classMapJson)
```
## 通过 Kotlin 的扩展函数访问：
```kotlin
    val xiaoMingJson = """{name:"XiaoMing",age:22}"""
    
    val student = xiaoMingJson.toBean<Student>()
    val list = classJson.toBean<List<Student>>()
```
## 源码非常少，也非常简单，原理就是通过 Kotlin 的内联函数 ( inline ) 和 具体化的类型参数 ( reified ) 特性，将泛型 "T" 通过 TypeToken 获取到 Type 进而进行反序列化。
### GSON 源码如下：
```kotlin
object GSON {
    /**
     * 初始化默认的 [com.google.gson.Gson] 转换器
     */
    var gson = GsonBuilder().setLenient().create()

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
```
### 扩展函数清单如下：
```kotlin
inline fun <reified T> String.toBean() = GSON.fromJson<T>(this)

inline fun <reified T> JsonElement.toBean() = GSON.fromJson<T>(this)

fun Any.toJson() = GSON.toJson(this)

fun JsonElement.toJson() = GSON.toJson(this)
```