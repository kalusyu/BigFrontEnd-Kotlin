package com.kalusyu.patterns

import java.io.*
import java.net.HttpURLConnection
import java.net.Proxy
import java.net.URL

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/6/24 18:31
 *
 **/

fun main() {
    /*val file = File("/a/b/c.a")
    println(file.name)// print c.a
    println(lazyValue) // 第一次调用执行lazy体
    println(lazyValue) // 第二次调用只返回get的值

    val user = User(mapOf(
        "name" to "Kalusyu",
        "age" to 20
    ))

    println("${user.name} + ${user.age}")

    val (name, age) = user

    println("destructor : $name + $age")*/

    val output: BufferedOutputStream? = null
    val br: BufferedInputStream? = null
    val url =
        URL("http://39.99.191.19:8888/test/aaaaa.zip")
    val httpConnection =
        url.openConnection(Proxy.NO_PROXY) as HttpURLConnection // 设置 User-Agent

    httpConnection.setRequestProperty("User-Agent", "NetFox") // 设置断点续传的开始位置

//        httpConnection.setRequestProperty("RANGE", "bytes=4000001-4710217"); // 获得输入流
    //        httpConnection.setRequestProperty("RANGE", "bytes=4000001-4710217"); // 获得输入流
    httpConnection.setRequestProperty("Range", "bytes=1000000-2500556")
//        httpConnection.setRequestProperty("Content-Range","bytes=2000000-4710217/4710217");
    //        httpConnection.setRequestProperty("Content-Range","bytes=2000000-4710217/4710217");
    val input: InputStream = httpConnection.inputStream
    val file = RandomAccessFile(File("aa3a.mp4"), "rw")
    val nPos: Long = 0
    // 定位文件指针到 nPos 位置
    // 定位文件指针到 nPos 位置
    file.seek(1000000)
    val b = ByteArray(1024)
    var nRead: Int = 0// 从输入流中读入字节流，然后写到文件中

    while (input.read(b).also { nRead = it } > 0) {
        file.write(b, 0, nRead)
    }
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

object DataObject{

    fun testData(){
        println("testData")
    }
}


private fun download2(url: String) {

}




