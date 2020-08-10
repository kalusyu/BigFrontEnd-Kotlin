package com.kalusyu.appstartup

import android.content.Context
import androidx.startup.Initializer
import java.util.*

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/6 17:31
 *
 **/
class Libc:Initializer<Sdk1> {
    override fun create(context: Context): Sdk1 {
        Sdk1.init()
        return Sdk1()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return Collections.emptyList()
    }
}