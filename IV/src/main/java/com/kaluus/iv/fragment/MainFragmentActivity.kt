package com.kaluus.iv.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.kaluus.iv.R

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/12/29 10:10
 *
 **/
class MainFragmentActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)

        val fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()

        val fragment = ListedFragment()
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.commit()


        // 一次commit 需要重新beginTransaction，否则报错
        fragmentTransaction = fragmentManager.beginTransaction()
        val standardFragment = StandardFragment()
        fragmentTransaction.replace(R.id.fragment_container, standardFragment)
        // fragment放入到堆栈管理
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()


    }

    override fun onResume() {
        super.onResume()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}