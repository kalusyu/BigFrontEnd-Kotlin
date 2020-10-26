package com.kalus.serialcomm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kongqw.serialportlibrary.SerialPortFinder
import com.kongqw.serialportlibrary.SerialPortManager
import com.kongqw.serialportlibrary.listener.OnOpenSerialPortListener
import com.kongqw.serialportlibrary.listener.OnSerialPortDataListener
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/10/26 13:54
 *
 **/
class MainActivity : AppCompatActivity() {

    private lateinit var mSerialPortManager: SerialPortManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serialPortFinder = SerialPortFinder()
        val devices = serialPortFinder.devices

        mSerialPortManager = SerialPortManager()


        mSerialPortManager.setOnOpenSerialPortListener(object : OnOpenSerialPortListener {
            override fun onSuccess(device: File?) {
                Log.e("ybw", "open onSuccess")
            }

            override fun onFail(device: File?, status: OnOpenSerialPortListener.Status?) {
                Log.e("ybw", "open onFail")
            }
        })

        mSerialPortManager.setOnSerialPortDataListener(object : OnSerialPortDataListener {
            override fun onDataReceived(bytes: ByteArray) {
                Log.e("ybw", "open onDataReceived bytes=$bytes")

            }

            override fun onDataSent(bytes: ByteArray) {
                //Log.e("ybw", "open onFail")
            }
        })

        serialTest.setOnClickListener {
            val openSerialPort =
                mSerialPortManager.openSerialPort(devices[0].file, 115200)
            if (openSerialPort) {
                Log.e("ybw", "open success")
            }
        }
    }
}
