package com.kaluus.interview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf("权限管理", "Fragment使用大全")
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
                else -> {

                }
            }
        }

        rvDemo.adapter = adapter
    }

    fun Context.showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}

