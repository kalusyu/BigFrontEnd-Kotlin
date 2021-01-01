package com.kaluus.iv.fragment.viewmodel

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import com.kaluus.iv.R

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/12/31 16:39
 *
 **/
class ViewModelActivity : AppCompatActivity() {

    companion object {
        private const val STATE_FRAGMENT = "state_fragment"
    }

    private val viewModel: SaveStateViewModel by viewModels {
        SavedStateViewModelFactory(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)
        val fragmentManager = supportFragmentManager
        // 查找fragment的TAG是否存在，如果存在则不加入，否则添加进入
        fragmentManager.findFragmentByTag(STATE_FRAGMENT) ?: handleAddFragment()

    }

    override fun onResume() {
        super.onResume()
        val fragment = supportFragmentManager.findFragmentByTag(STATE_FRAGMENT)
        supportFragmentManager.beginTransaction().hide(fragment!!)
            .commitAllowingStateLoss()
    }

    private fun handleAddFragment() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        // 旋转屏幕出现数据重影问题
        val fragment = SaveStateFragment(23)
        transaction.add(R.id.fragment_container, fragment, STATE_FRAGMENT)
        transaction.commit()
    }
}