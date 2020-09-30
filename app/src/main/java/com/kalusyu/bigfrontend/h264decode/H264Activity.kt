package com.kalusyu.bigfrontend.h264decode

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kalusyu.bigfrontend.R
import com.kalusyu.bigfrontend.rtsp.RtspClient
import kotlinx.android.synthetic.main.activity_h264.*

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/17 10:11
 *
 **/
class H264Activity : AppCompatActivity() {
    lateinit var client: RtspClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_h264)
//        val surfaceHolder = h264Surface.holder
//        surfaceHolder.addCallback(object:SurfaceHolder.Callback{
//            override fun surfaceChanged(
//                holder: SurfaceHolder?,
//                format: Int,
//                width: Int,
//                height: Int
//            ) {
//
//            }
//
//            override fun surfaceDestroyed(holder: SurfaceHolder?) {
//            }
//
//            override fun surfaceCreated(holder: SurfaceHolder?) {
//
//            }
//        })

        var addr = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov"
//        addr = "rtsp://192.168.1.50:554/"
        client = RtspClient("udp", addr, null, null)
        btn.setOnClickListener {
            h264Surface.visibility = View.VISIBLE
            it.visibility = View.INVISIBLE
            client.setSurfaceView(h264Surface)
            client.start()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        client?.shutdown()
    }
}