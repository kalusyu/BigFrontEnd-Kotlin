package com.kalusyu.patterns

import java.lang.IllegalArgumentException

/**
 * desc:提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。
 *
 * 抽象工厂和抽象产品，多于一个的产品
 *
 * @author biaowen.yu
ate 2020/7/3 14:59
 *
 **/
/**
 * 抽象工厂
 */
interface AbstractFactory {
    fun createPen(type: String): Pen
    fun createEraser(type: String): Eraser

    companion object {
        @JvmStatic
        fun createFactory(name: String) =
            when (name){
                "Xiaomi"->{
                    XiaomiFactory()
                }
                "Huawei"->{

                }
                else->{
                    throw IllegalArgumentException("")
                }
            }
    }
}

/**
 * 抽象产品A
 */
interface Pen {
    fun color()
    fun material()
}

/**
 * 抽象产品B
 */
interface Eraser {
    fun shape()
}

// <editor-folder desc="小米厂商">
class XiaomiPen : Pen {
    override fun color() {
        TODO("Not yet implemented")
    }

    override fun material() {
        TODO("Not yet implemented")
    }
}

class XiaomiEraser : Eraser {
    override fun shape() {
        TODO("Not yet implemented")
    }
}

class XiaomiFactory : AbstractFactory {
    override fun createPen(type: String): Pen {
        TODO("Not yet implemented")
    }

    override fun createEraser(type: String): Eraser {
        TODO("Not yet implemented")
    }
}
// </editor-folder>

// <editor-folder desc="华为厂商">
class HuaweiPen : Pen {
    override fun color() {
        TODO("Not yet implemented")
    }

    override fun material() {
        TODO("Not yet implemented")
    }
}

class HuaweiEraser : Eraser {
    override fun shape() {
        TODO("Not yet implemented")
    }
}

class HuaweiFactory : AbstractFactory {
    override fun createPen(type: String): Pen {
        TODO("Not yet implemented")
    }

    override fun createEraser(type: String): Eraser {
        TODO("Not yet implemented")
    }
}

// </editor-folder>
