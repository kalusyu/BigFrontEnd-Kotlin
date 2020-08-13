package com.kalusyu.hiltpractice.hilt

import javax.inject.Inject

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/13 19:52
 *
 **/
class HiltSimple @Inject constructor() {

    fun doSth() {
        System.err.println("doSth....")
    }
}