package com.kalusyu.hiltpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.kalusyu.hiltpractice.hilt.AnalyticsAdapter
import com.kalusyu.hiltpractice.hilt.HiltViewModel
import com.kalusyu.hiltpractice.hilt.HoldContext
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel:HiltViewModel by viewModels()

    @Inject
    lateinit var analyticsAdapter: AnalyticsAdapter

    @Inject lateinit var holdContext: HoldContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvHilt.setOnClickListener {
            viewModel.insert()
            analyticsAdapter.saySomething()
        }
    }
}
