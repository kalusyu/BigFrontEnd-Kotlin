package com.kalusyu.patterns

import java.io.File

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/6/24 18:31
 *
 **/

fun main() {
    val file = File("/a/b/c.a")
    println(file.name)// print c.a
    println(lazyValue) // 第一次调用执行lazy体
    println(lazyValue) // 第二次调用只返回get的值

    val user = User(mapOf(
        "name" to "Kalusyu",
        "age" to 20
    ))

    println("${user.name} + ${user.age}")

    val (name, age) = user

    println("destructor : $name + $age")
}

val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}


class User(val map: Map<String, Any?>) {
    operator fun component1() = name

    operator fun component2() = age

    val name: String by map
    val age: Int by map


}





