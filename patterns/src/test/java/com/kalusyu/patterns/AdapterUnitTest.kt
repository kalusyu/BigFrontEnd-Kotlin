package com.kalusyu.patterns

import com.kalusyu.patterns.struct.ChinesePluginProduct
import com.kalusyu.patterns.struct.ChineseToEnglishPluginAdapter
import com.kalusyu.patterns.struct.RunnableAdapter
import com.kalusyu.patterns.struct.Task
import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/15 9:11
 *
 **/
class AdapterUnitTest {

    @Test
    fun testAdapter() {
        val task = Task(12323L)
        val adapter = RunnableAdapter(task)
        val thread = Thread(adapter)
        thread.start()

        val chinesePluginProduct = ChinesePluginProduct()
        val chineseToEnglishPluginAdapter = ChineseToEnglishPluginAdapter(chinesePluginProduct)
        chineseToEnglishPluginAdapter.largeHole()
    }
}