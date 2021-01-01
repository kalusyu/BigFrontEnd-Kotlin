package com.kaluus.iv.fragment.newapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kaluus.iv.R
import com.kaluus.iv.fragment.newapi.NewApiActivity.Companion.FRAGMENT_RESULT1
import kotlinx.android.synthetic.main.single_button.*

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2021/1/1 11:19
 *
 **/
class NewApiFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.single_button, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        testBtn.setOnClickListener {
            val result = Bundle().apply {
                putString("userId", "123")
                putString("userName", "KalusYu")
            }
            parentFragmentManager.setFragmentResult(FRAGMENT_RESULT1, result)
        }
    }

}