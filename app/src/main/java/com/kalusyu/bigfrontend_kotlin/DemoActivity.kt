package com.kalusyu.bigfrontend_kotlin

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.TimeUtils
import com.kalusyu.bigfrontend_kotlin.camerax.CameraActivity
import com.kalusyu.bigfrontend_kotlin.mediacodec.MediaCodecActivity
import kotlinx.android.synthetic.main.demo_layout.*


/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/16 10:59
 *
 **/
class DemoActivity : AppCompatActivity() {
    private val PERMISSIONS_REQUIRED =
        listOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo_layout)
        btn1.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btn3.setOnClickListener {
            val intent = Intent(this, MediaCodecActivity::class.java)
            startActivity(intent)
        }


        if (!hasPermissions(this)) {
            ActivityCompat.requestPermissions(
                this,
                PERMISSIONS_REQUIRED.toTypedArray(),
                1
            )
        }
        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val bestProvider = manager.getBestProvider(getCriteria(), true)
        val location = if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        } else {
            manager.getLastKnownLocation(bestProvider)
        }
        location?.let {
            Log.e("ybw", "ybw = ${TimeUtils.millis2String(it.time, " yyyy-MM-dd HH:mm:ss ")}")
        }


    }


    private fun getCriteria(): Criteria? {
        val criteria = Criteria()
        // 设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
        criteria.accuracy = Criteria.ACCURACY_FINE
        // 设置是否要求速度
        criteria.isSpeedRequired = false
        // 设置是否允许运营商收费
        criteria.isCostAllowed = false
        // 设置是否需要方位信息
        criteria.isBearingRequired = false
        // 设置是否需要海拔信息
        criteria.isAltitudeRequired = true
        // 设置对电源的需求
        criteria.powerRequirement = Criteria.POWER_LOW
        return criteria
    }


    fun hasPermissions(context: Context?): Boolean {
        for (permission in PERMISSIONS_REQUIRED) {
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    permission!!
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }


}