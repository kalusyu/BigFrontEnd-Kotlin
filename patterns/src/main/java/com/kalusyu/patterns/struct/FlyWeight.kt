package com.kalusyu.patterns.struct

/**
 * desc:运用共享技术有效地支持大量细粒度的对象。
 * 如果一个对象实例一经创建就不可变，那么反复创建相同的实例就没有必要，直接向调用方返回一个共享的实例就行，这样即节省内存，又可以减少创建对象的过程，提高运行速度。
 * @author biaowen.yu
 * @date 2020/7/14 10:05
 *
 **/
class FlyWeight {
    // 比如Integer.valueOf(100)就是享元模式，享元模式 实际用于缓存
    val studentCache = mutableMapOf<String, Student>()

    fun createStudent(name: String): Student? {
        var student: Student?
        if (studentCache[name] == null) {
            student = Student(name, 10)
            studentCache[name] = student
        } else {
            student = studentCache[name]
        }
        return student
    }
}

data class Student(val name: String, val age: Int)