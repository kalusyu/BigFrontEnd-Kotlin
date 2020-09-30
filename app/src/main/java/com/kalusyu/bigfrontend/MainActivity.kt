package com.kalusyu.bigfrontend

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.kalusyu.bigfrontend.opengl.MyGLSurfaceView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.DataInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.Socket

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

//            playRtsp(addr)
            openGlSurface.setYuvDataSize(640, 480)
            object : Thread() {
                override fun run() {
                    bindYuvSocket()
                }
            }.start()
        }
    }

    private fun bindYuvSocket() {
        val socket = Socket("192.168.1.50", 20006)
        val outputStream = socket.getOutputStream()
        val inputStream = socket.getInputStream()
        val blocks = ByteArray(640 * 480 * 2)
//        val blocks = ByteArray(61440)
        var len = -1
        Log.e("ybw", "yuv back")
        val file = File("sdcard", "video.yuv")
        val fos = FileOutputStream(file)

        val buf = ByteArray(4)
        while (inputStream.read(buf, 0, buf.size).also { len = it } != 0) {
            if (len == 4) {
                if (buf[0] == 0x0.toByte() && buf[1] == 0x0.toByte() && buf[2] == 0x0.toByte() && buf[3] == 0x1.toByte()) {
                    println("had read the head byte")
                    val dis = DataInputStream(inputStream)
                    val readLength = dis.readInt()
                    val buffer = ByteArray(readLength)
                    dis.read(buffer, 0 ,buffer.size)
                    runOnUiThread{
                        val bitmap = BitmapFactory.decodeByteArray(buffer,0,buffer.size)
                        img_rgb.setImageBitmap(bitmap)
                    }

                }
            }
        }

        /*val buf = ByteArray(4)
        while (inputStream.read(buf, 0, buf.size).also { len = it } != 0) {
            if (len == 4) {
                if (buf[0] == 0x0.toByte() && buf[1] == 0x0.toByte() && buf[2] == 0x0.toByte() && buf[3] == 0x1.toByte()) {
                    println("had read the head byte")
                    val yuvLen = 640 * 480 * 2
                    var readL = 0
                    val writeBuf = ByteArray(640 * 480 * 2)
                    while (true) {
                        val read = inputStream.read(blocks)
                        readL += read

                        println("readL = $readL")
                        if (readL >= yuvLen) {
                            break
                        } else {
                        System.arraycopy(blocks, 0, writeBuf, readL, if (writeBuf.size - readL < read)  writeBuf.size - readL else read)
                        }

                    }
                    if (readL != 0) {
                        println("yuv len $readL")
//                        fos.write(writeBuf, 0, yuvLen)
                        runOnUiThread{
                            openGlSurface.feedData(blocks)
                        }
                    } else {
                        break
                    }
                }
            }
        }*/

//        while (inputStream.read(blocks).also { len = it } != 0) {
//            runOnUiThread {
//                println("len=$len")
////                openGlSurface.feedData(blocks)
////                val bitmap = BitmapFactory.decodeByteArray(blocks,0,blocks.size)
////                val buf = ByteArray(len)
////                System.arraycopy(blocks, 0, buf, 0, len)
//                val bitmap = MyBitmapFactory.createMyBitmap(blocks, 640, 480)
//                img_rgb.setImageBitmap(bitmap)
//
//                fos.write(blocks, 0, len)
//            }
//        }
        inputStream.close()

    }

    private fun playRtsp(addr: String) {

        /*val client = RtspClient(addr)
        client.setSurfaceView(surfaceView)
        client.setGlSurfaceView(openGlSurface)
        client.start()*/


//        val h264Stream = H264Stream.getTest()
//        h264Stream.nativeInit()
//        h264Stream.setGlSurfaceView(openGlSurface)
//        h264Stream.initYuvFile()
//        staticopenGlSurface = openGlSurface
//
//        val inputStream = assets.open("test2.h264")
//        val byteArray = ByteArray(4 * 1024)
//        var count = 0
//        while (inputStream.read(byteArray, 0, byteArray.size) != -1) {
//            Log.i("ybw", "read ${++count}")
//            h264Stream.setH624Stream(byteArray)
//        }
//        Log.i("ybw", "read finish")


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

        }

        @JvmStatic
        var staticopenGlSurface: MyGLSurfaceView? = null
    }
}
