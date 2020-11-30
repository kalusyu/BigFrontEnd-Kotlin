package com.example.jetpacklifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/11/30 10:18
 *
 **/
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object :
            Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                Log.d(TAG, "Activity class: $p0")
            }

            override fun onActivityStarted(p0: Activity) {
            }

            override fun onActivityResumed(p0: Activity) {
            }

            override fun onActivityPaused(p0: Activity) {
            }

            override fun onActivityStopped(p0: Activity) {
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            }

            override fun onActivityDestroyed(p0: Activity) {
            }
        })
    }
}