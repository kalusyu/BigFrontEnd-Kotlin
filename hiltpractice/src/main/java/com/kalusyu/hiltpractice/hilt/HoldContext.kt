package com.kalusyu.hiltpractice.hilt

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/18 20:05
 *
 **/
class HoldContext @Inject constructor(@ActivityContext private val context:Context) {
}