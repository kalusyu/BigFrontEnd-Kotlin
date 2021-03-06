package com.kalusyu.patterns

import com.kalusyu.patterns.struct.CircleView
import com.kalusyu.patterns.struct.Composite
import com.kalusyu.patterns.struct.Rectangle
import com.kalusyu.patterns.struct.composite.*
import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/15 16:20
 *
 **/
class CompositeUnitTest {

    @Test
    fun testComposite() {
        val circleView = CircleView()
        val rectangle:Rectangle = Rectangle()
        val composite = Composite()
        composite.addView(circleView)
        composite.addView(rectangle)
        composite.draw()
    }

    @Test
    fun testActionTrain(){
        val keepCount = KeepCountTrain()
        val keepTime = KeepTimeTrain()
        val keepCountTime = KeepCountTimeTrain()

        val deviceTrain = DeviceTrain()
        deviceTrain.addAction(keepCount)

        deviceTrain.doActionTrain()

        val freeTrain = FreeTrain()
        freeTrain.addAction(keepTime)
        freeTrain.doActionTrain()
    }
}