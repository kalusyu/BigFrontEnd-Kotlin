package com.kalusyu.bigfrontend_kotlin.h264decode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import com.kalusyu.bigfrontend_kotlin.R
import kotlinx.android.synthetic.main.activity_h264.*

class H264Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_h264)
        val surfaceHolder = h264Surface.holder
        surfaceHolder.addCallback(object:SurfaceHolder.Callback{
            override fun surfaceChanged(
                holder: SurfaceHolder?,
                format: Int,
                width: Int,
                height: Int
            ) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
            }

            override fun surfaceCreated(holder: SurfaceHolder?) {

            }
        })
    }
}