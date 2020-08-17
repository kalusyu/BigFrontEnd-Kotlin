package com.kalusyu.bigfrontend_kotlin.custominput

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kalusyu.bigfrontend_kotlin.R
import kotlinx.android.synthetic.main.keyboard_layout.*


/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/17 15:53
 *
 **/
class KeyboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.keyboard_layout)

        var carKeyBoardUtil = CarKeyBoardUtil(ky_keyboard_parent, ky_keyboard, act_key_board_et)
        act_key_board_et.setOnTouchListener { v, event ->
            if (carKeyBoardUtil == null) {
                carKeyBoardUtil =
                    CarKeyBoardUtil(ky_keyboard_parent, ky_keyboard, act_key_board_et)
            }
            carKeyBoardUtil.showKeyboard()
            false
        }
    }
}