package com.ddd4.dropit.presentation.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.usecase.SetSectionUseCase
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class SplashViewModel @ViewModelInject constructor(
    private val setSectionUseCase: SetSectionUseCase
): BaseViewModel() {

    val navigateToMain = SingleLiveEvent<Long>()

    init {
        setSectionFromJson()
    }

    private fun setSectionFromJson() {
        viewModelScope.launch {
            try {
                setSectionUseCase()
                navigateToMain.call()

            } catch (e: Exception) {
                Timber.d(e)
            }
        }
    }
}