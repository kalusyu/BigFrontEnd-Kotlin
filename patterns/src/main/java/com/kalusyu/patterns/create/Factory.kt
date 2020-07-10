package com.kalusyu.patterns.create

/**
 * desc:定义一个用于创建对象的接口，让子类决定实例化哪一个类。Factory Method使一个类的实例化延迟到其子类。
 *
 * @author biaowen.yu
 * @date 2020/7/8 9:19
 *
 **/
interface Shape {
    fun draw()
}

class Rectangle : Shape {
    override fun draw() {
        println("Rectangle draw")
    }
}

class Circle : Shape {

    override fun draw() {
        println("Circle draw")
    }
}

class TriAngle : Shape {
    override fun draw() {
        println("TriAngle draw")
    }
}

class Factory {
    fun getShape(type: String) =
        when (type) {
            "circle" -> {
                Circle()
            }
            "angle" -> {
                TriAngle()
            }
            "rectangle" -> {
                Rectangle()
            }
            else -> {
                throw IllegalArgumentException("")
            }
        }

}
