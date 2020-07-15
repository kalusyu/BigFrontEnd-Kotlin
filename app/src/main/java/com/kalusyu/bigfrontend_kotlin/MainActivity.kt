package com.kalusyu.bigfrontend_kotlin

import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.kalusyu.bigfrontend_kotlin.opengl.MyGLSurfaceView
import com.kalusyu.bigfrontend_kotlin.rtspclient.RtspClient
import com.kalusyu.bigfrontend_kotlin.rtspclient.internal.video.H264Stream
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

//        val client = RtspClient(addr)
//        client.setSurfaceView(surfaceView)
//        client.setGlSurfaceView(openGlSurface)
//        client.start()
        val h264Stream = H264Stream.getTest()
        h264Stream.nativeInit()
        h264Stream.setGlSurfaceView(openGlSurface)
        h264Stream.initYuvFile()
        staticopenGlSurface = openGlSurface

        val inputStream = assets.open("test2.h264")
        val byteArray = ByteArray(4 * 1024)
        var count = 0
        while (inputStream.read(byteArray, 0, byteArray.size) != -1) {
            Log.i("ybw", "read ${++count}")
            h264Stream.setH624Stream(byteArray)
        }
        Log.i("ybw", "read finish")


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

        @JvmStatic
        var staticopenGlSurface: MyGLSurfaceView? = null
    }
}
