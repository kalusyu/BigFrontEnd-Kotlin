package com.kalusyu.hiltpractice

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/11 9:40
 *
 **/
class MyApplication:Application() {

    val appContainer = AppContainer()
}