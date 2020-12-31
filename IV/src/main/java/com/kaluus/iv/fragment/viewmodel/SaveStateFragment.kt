package com.kaluus.iv.fragment.viewmodel

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.kaluus.iv.R
import com.kaluus.iv.databinding.SavedStateBinding

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/12/31 14:41
 *
 **/
class SaveStateFragment : Fragment() {


    private val viewModel :SaveStateViewModel by activityViewModels()

    /*private val viewModel2 = ViewModelProvider(
        this,
        SavedStateViewModelFactory(requireActivity().application, this)
    ).get(
        SaveStateViewModel::class.java
    )*/


    //Activity使用 DataBindingUtil.setContentView(this, R.layout.test_activity);
    // fragmetn使用 DataBindingUtil.inflate(inflater, R.layout.test_fragment_idcard,container,false);
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("====onCreateView")
        val binding = DataBindingUtil.inflate<SavedStateBinding>(
            inflater,
            R.layout.saved_state,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.data = viewModel
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("====onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("====onCreate")
        // 节省资源，不用创建Fragment实例
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("====onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println("====onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        println("====onStart")
    }

    override fun onResume() {
        super.onResume()
        println("====onResume")
    }

    override fun onPause() {
        super.onPause()
        println("====onPause")
    }


    override fun onStop() {
        super.onStop()
        println("====onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        println("====onSaveInstanceState")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        println("====onViewStateRestored")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("====onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("====onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        println("====onDetach")
    }
}