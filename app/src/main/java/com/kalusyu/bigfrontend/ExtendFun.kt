package com.kalusyu.bigfrontend

import android.content.Context
import android.widget.Toast

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/7 14:03
 *
 **/

inline fun Context.toast(msg:String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}