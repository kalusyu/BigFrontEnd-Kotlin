package com.kaluus.iv.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaluus.iv.R

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/12/29 11:40
 *
 **/
class DemoFragmentActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_layout)
    }
}