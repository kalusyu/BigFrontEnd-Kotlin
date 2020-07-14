package com.kalusyu.patterns

import com.kalusyu.patterns.struct.FlyWeight
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/14 10:38
 *
 **/
class FlyWeightUnitTest {

    @Test
    fun testFlyWeight() {
        val flyWeight = FlyWeight()
        val student = flyWeight.createStudent("kalusyu")
        val student1 = flyWeight.createStudent("kalusyu")
        Assertions.assertThat(student).isSameAs(student1)
    }
}