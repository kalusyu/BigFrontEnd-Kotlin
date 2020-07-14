package com.kalusyu.patterns.struct

/**
 * desc:动态地给一个对象添加一些额外的职责。就增加功能来说，相比生成子类更为灵活。
 *
 * @author biaowen.yu
 * @date 2020/7/10 9:18
 *
 **/
interface CoffeeMachine {
    fun makeCoffee()
    fun makeWater()
}

class NormalCoffeeMachine : CoffeeMachine {
    override fun makeCoffee() = println("Normal:make coffee")

    override fun makeWater() = println("Normal:make water")
}

//:Decorator 剩下的抽象接口可以由coffeeMachine 代理
class EnhanceCoffeeMachine(private val coffeeMachine: CoffeeMachine) : CoffeeMachine by coffeeMachine {
    override fun makeCoffee() {
        println("Enhance: make coffee")
    }

    fun makeCoffeeWithMilk() {
        println("Enhanced: Making coffee with milk")
        coffeeMachine.makeCoffee()
        addMilk()
    }

    private fun addMilk() {
        println("Enhanced: Adding milk")
    }

}