package com.kalusyu.patterns

/**
 * desc:
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
