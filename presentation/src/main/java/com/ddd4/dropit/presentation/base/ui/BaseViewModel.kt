package com.ddd4.dropit.presentation.base.ui

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel : ViewModel() {

    //inline fun <T> launchOnViewModelScope(crossinline block: suspend () -> LiveData<T>): LiveData<T> {
    //    return liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
    //        emitSource(block())
    //    }
    //}

    val isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()

        hideLoading()
    }

    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }
}