package com.kaluus.iv.fragment.newapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentResultListener
import com.kaluus.iv.R

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2021/1/1 11:19
 *
 **/
class NewApiActivity : AppCompatActivity() {

    companion object {
        const val TAG = "NewApiActivity"
        const val FRAGMENT_RESULT1 = "fragment_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)

        supportFragmentManager.setFragmentResultListener(FRAGMENT_RESULT1, this,
            FragmentResultListener { requestKey, result ->
                when (requestKey) {
                    FRAGMENT_RESULT1 -> {
                        val id = result["userId"]
                        val name = result["userName"]
                        Log.e(TAG, "result = ($id,$name)")
                    }
                }
            })
        val fragment = NewApiFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
    }
}