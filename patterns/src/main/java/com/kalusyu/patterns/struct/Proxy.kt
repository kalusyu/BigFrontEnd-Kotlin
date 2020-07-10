package com.kalusyu.patterns.struct

/**
 * desc:为其他对象提供一种代理以控制对这个对象的访问。
 *
 * @author biaowen.yu
 * @date 2020/7/10 9:18
 *
 **/
interface File {
    fun read()
}

class NormalFile : File {
    override fun read() {
        println("read file normal")
    }
}

class SecurityFile(val normalFile: NormalFile) : File {

    var password = ""

    override fun read() {
        if (password == "secret") {
            println("Password is correct: $password")
            normalFile.read()
        } else {
            println("Incorrect password. Access denied!")
        }
    }
}
