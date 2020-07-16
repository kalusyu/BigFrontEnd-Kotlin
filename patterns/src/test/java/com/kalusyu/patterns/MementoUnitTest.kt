package com.kalusyu.patterns

import com.kalusyu.patterns.behavior.CareTaker
import com.kalusyu.patterns.behavior.Original
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/16 15:12
 *
 **/
class MementoUnitTest {

    @Test
    fun testMemento(){
        val original = Original("init")
        val careTaker = CareTaker()
        careTaker.saveState(original.createMemento())

        original.state = "state1"
        original.state = "state2"
        careTaker.saveState(original.createMemento())
        var memento = careTaker.restoreState(1)
        println("memento state : ${memento.state}")
        Assertions.assertThat(memento.state == "state2")

        memento = careTaker.restoreState(0)
        println("memento state : ${memento.state}")
        Assertions.assertThat(memento.state == "init")

    }
}