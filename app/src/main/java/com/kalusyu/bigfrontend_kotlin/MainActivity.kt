package com.kalusyu.bigfrontend_kotlin

import android.os.Bundle
import android.view.SurfaceView
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.kalusyu.bigfrontend_kotlin.rtspclient.RtspClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var surfaceView: SurfaceView? = null
    private var videoView: VideoView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
            ),
            1002
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
//        sample_text.text = stringFromJNI()

        surfaceView = findViewById(R.id.surface)
        videoView = findViewById(R.id.videoView)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val addr = "rtsp://192.168.1.152:554/"
//            val addr = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov"
//            val player = MediaPlayer()
//            player.setDataSource(addr)
//            player.setSurface(surfaceView!!.holder.surface)
//            player.prepareAsync()
//            player.setOnPreparedListener {
//                player.start()
//            }

//            videoView!!.setVideoPath(addr)
//            videoView!!.requestFocus()
//            videoView!!.start()

            playRtsp(addr)

        }

    }

    private fun playRtsp(addr: String) {

        val client = RtspClient(addr)
        client.setSurfaceView(surfaceView)
        client.setGlSurfaceView(openGlSurface)
        client.start()

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
//            System.loadLibrary("showYUV")
            System.loadLibrary("native-lib")
        }
    }
}
