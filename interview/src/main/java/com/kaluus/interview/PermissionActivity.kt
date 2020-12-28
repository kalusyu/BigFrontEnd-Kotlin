package com.kaluus.interview

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionActivity :AppCompatActivity(){
    private lateinit var i: Intent
    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> {}
                2 -> {
                    startActivity(i)
                    finish()
                }
            }
        }
    }
    private var timeTv: TextView? = null
    private var hasrefuse = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        //判断用户是否给这些权限授权

        //判断用户是否给这些权限授权
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) !== PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) !== PackageManager.PERMISSION_GRANTED
        ) {

            //判断是否拒绝过
            hasrefuse = ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CALL_PHONE
            )
            hasrefuse = ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_CONTACTS
            )
            if (hasrefuse) {
                //当拒绝了授权后，为提升用户体验，可以以弹窗的方式引导用户到设置中去进行设置
                AlertDialog.Builder(this)
                    .setMessage("需要开启权限才能使用此功能")
                    .setPositiveButton("设置"){ dialogInterface: DialogInterface, i: Int ->
                        //引导用户到设置中去进行设置
                        val intent = Intent()
                        intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                        intent.data = Uri.fromParts("package", packageName, null)
                        startActivity(intent)
                        finish()
                    }
                    .setNegativeButton("取消"){ dialogInterface: DialogInterface, i: Int ->
                        finish()
                    }
                    .create()
                    .show()
            } else {
                //如果没有拒绝过,进入回调
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.CALL_PHONE
                    ),
                    1
                )
            }
        } else {
            object : Thread() {
                override fun run() {
                    super.run()
                    //引导页倒计时
                    for (j in 5 downTo 1) {
                        try {
                            sleep(1000)
                            val msg: Message = Message.obtain()
                            msg.what = 1
                            msg.obj = j
                            handler.sendMessage(msg)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    }
                    handler.sendEmptyMessage(2)
                }
            }.start()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                startActivity(i)
                finish()
            } else {
                Toast.makeText(this, "请给与权限", Toast.LENGTH_SHORT).show()

                //引导用户到设置中去进行设置
                val intent = Intent()
                intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                intent.data = Uri.fromParts("package", packageName, null)
                startActivity(intent)
                finish()
            }
        }
    }


}
