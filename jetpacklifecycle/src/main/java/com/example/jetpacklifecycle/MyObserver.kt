package com.example.jetpacklifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

const val TAG = "ybw"
/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/11/26 17:25
 *
 **/
class MyObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectListener(){
        Log.w(TAG, "onResume connectListener")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnectListener(){
        Log.w(TAG, "onPause disconnectListener")
    }
}