package com.kalusyu.hiltpractice

import android.app.Application
import com.kalusyu.hiltpractice.byhand.AppContainer
import com.kalusyu.hiltpractice.hilt.HiltSimple
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/11 9:40
 *
 **/
@HiltAndroidApp
class MyApplication:Application() {

    // by hand
//    val appContainer = AppContainer()

    @Inject
    lateinit var hiltSimple: HiltSimple
    override fun onCreate() {
        super.onCreate()
        hiltSimple.doSth()
    }
}