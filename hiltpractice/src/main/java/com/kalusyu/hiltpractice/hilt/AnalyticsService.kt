package com.kalusyu.hiltpractice.hilt

import javax.inject.Inject

/**
 * desc: @Bind 向接口注入实例
 *
 * @author biaowen.yu
 * @date 2020/8/11 21:02
 *
 **/
interface AnalyticsService {
    fun analyticsMethods()
}


class AnalyticsServiceImpl @Inject constructor():AnalyticsService{
    override fun analyticsMethods() {
        println("AnalyticsServiceImpl @Bind analyticsMethods")
    }
}