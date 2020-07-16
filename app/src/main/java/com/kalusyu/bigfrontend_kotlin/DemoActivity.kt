package com.kalusyu.bigfrontend_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kalusyu.bigfrontend_kotlin.camerax.CameraActivity
import kotlinx.android.synthetic.main.demo_layout.*

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/16 10:59
 *
 **/
class DemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo_layout)
        btn1.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
    }
}