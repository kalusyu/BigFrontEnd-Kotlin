package com.kaluus.iv

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.kaluus.iv.fragment.DemoFragmentActivity
import com.kaluus.iv.fragment.MainFragmentActivity
import com.kaluus.iv.fragment.newapi.NewApiActivity
import com.kaluus.iv.fragment.viewmodel.ViewModelActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(TAG, "onCreate")

        val list = mutableListOf(
            "权限管理",
            "Fragment使用大全",
            "DemoFragment",
            "ViewModelFragment",
            "Fragment New API"
        )
        val adapter =
            object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.layout_simple, list) {
                override fun convert(holder: BaseViewHolder, item: String) {
                    holder.setText(R.id.tvDemo, item)
                }
            }
        adapter.setOnItemClickListener { adapter, view, position ->
            showToast("position=$position")
            when (position) {
                0 -> {
                    startActivity(Intent(this, PermissionActivity::class.java))
                }
                1 -> {
                    startActivity(Intent(this, MainFragmentActivity::class.java))
                }
                2 -> {
                    startActivity(Intent(this, DemoFragmentActivity::class.java))
                }
                3 -> {
                    startActivity(Intent(this, ViewModelActivity::class.java))
                }
                4 -> {
                    startActivity(Intent(this, NewApiActivity::class.java))
                }
                else -> {

                }
            }
        }

        rvDemo.adapter = adapter
    }

    fun Context.showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart")
    }

    // 非生命周期方法

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e(TAG, "onConfigurationChanged")
    }
}

