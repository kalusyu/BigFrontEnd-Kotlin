package com.kalusyu.patterns

import com.kalusyu.patterns.create.PrintDriver
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/14 9:12
 *
 **/

class SingletonUnitTest{

    @Test
    fun testSingleton(){
        val print1 = PrintDriver.print()
        val print2 = PrintDriver.print()

        Assertions.assertThat(print1 == print2)
        Assertions.assertThat(print1).isSameAs(print2)
    }
}