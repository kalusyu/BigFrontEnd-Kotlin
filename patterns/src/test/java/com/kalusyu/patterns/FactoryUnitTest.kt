package com.kalusyu.patterns

import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/8 9:36
 *
 **/
class FactoryUnitTest {

    @Test
    fun testSimpleFactory(){
        val factory = Factory()
        var shape = factory.getShape("circle")
        shape.draw()
        shape = factory.getShape("angle")
        shape.draw()
        shape = factory.getShape("rectangle")
        shape.draw()

    }
}