package com.kaluus.iv.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SaveStateViewModel(val state: SavedStateHandle) : ViewModel() {

    companion object {
        private val USER_KEY = "userId"
        private val NUMBER_KEY = "numberId"

    }

    fun saveCurrentUser(userId: String) {
        state.set(USER_KEY, userId)
    }

    fun getCurrentUser(): String {
        return state[USER_KEY] ?: ""
    }

    val number: MutableLiveData<Int> = state.getLiveData<Int>(NUMBER_KEY)

    fun add(n: Int) {
        number?.let {
            var i = it.value ?: 0
            it.value = i + n
            println("${it.value}")
        }
    }
}