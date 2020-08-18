package com.kalusyu.hiltpractice.hilt

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/15 12:47
 *
 **/
class HiltViewModel @ViewModelInject constructor(
    val personDao: PersonDao,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun insert() {
        val thread = Thread(Runnable {
            personDao.insert(PersonEntity(name = "kalus", updateTime = System.currentTimeMillis()))
        })
        thread.start()

    }
}