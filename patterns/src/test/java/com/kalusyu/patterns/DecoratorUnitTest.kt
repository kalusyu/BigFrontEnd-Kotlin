package com.kalusyu.patterns

import com.kalusyu.patterns.struct.EnhanceCoffeeMachine
import com.kalusyu.patterns.struct.NormalCoffeeMachine
import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/14 9:32
 *
 **/

class DecoratorUnitTest {
    @Test
    fun testDecorator() {
        val normalCoffeeMachine = NormalCoffeeMachine()
        val enhanceMachine = EnhanceCoffeeMachine(normalCoffeeMachine)

        enhanceMachine.makeCoffee()
        enhanceMachine.makeWater()
        enhanceMachine.makeCoffeeWithMilk()
    }
}