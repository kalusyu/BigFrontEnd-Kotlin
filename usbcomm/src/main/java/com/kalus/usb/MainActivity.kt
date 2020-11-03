package com.kalus.usb

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.MulticastSocket
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {

    companion object {
        const val BROADCAST_PORT = 12345
        const val BROADCAST_IP = "225.0.0.37"
        const val TAG = "ybw"
    }

    private var socket: MulticastSocket? = null
    private var address: InetAddress? = null

    fun startWork() {
        Thread {
            try {
//                sendMultiBroadcast()
                send("12345",12345)
                handler.sendEmptyMessageDelayed(1, 1000)
            } catch (e: Exception) {
                Log.e(TAG, "e=$e")
            }
        }.start()
    }


    private val handler = Handler {
        startWork()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usbTest.setOnClickListener {
            ActivityCompat.startActivity(this, Intent(this, UsbTestActivity::class.java), null)
        }

        try {
            socket = MulticastSocket(BROADCAST_PORT)
            address = InetAddress.getByName(BROADCAST_IP)
            socket?.timeToLive = 2
            socket?.joinGroup(address)
        } catch (e: Exception) {
            Log.e(TAG, "e=$e")
        }

        val t = Thread {
            try {
                sendMultiBroadcast()
            } catch (e: Exception) {
                Log.e(TAG, "e=$e")
            }
        }

        oneKey.setOnClickListener {
            t.start()
        }

        handler.sendEmptyMessageDelayed(1, 1000)


    }

    private fun sendMultiBroadcast() {
        Log.d(TAG, "sendMultiBroadcast")
        var edit = etPassword?.text?.toString()
        if (TextUtils.isEmpty(edit)) {
            /*showText("Password must not be null")
            return*/
            edit = "12345678910"
        }

        val buf = edit?.toByteArray(Charset.defaultCharset())
        buf?.run {

            val packet = DatagramPacket(buf, buf.size, address, BROADCAST_PORT)
            try {
                Log.i(TAG, "password = $edit")
                socket?.send(packet)
            } catch (e: Exception) {
                Log.e(TAG, "e=$e")
            }
        }
    }

    @Throws(IOException::class)
    fun send(msg: String, port: Int) {
        Log.d(TAG, "ybw msg=$msg")
        val ds = DatagramSocket()
        val dp = DatagramPacket(
            msg.toByteArray(), msg.toByteArray().size,
            InetAddress.getByName("192.168.0.255"), port
        )
        ds.send(dp)
        ds.close()
    }


}

fun Context.showText(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
