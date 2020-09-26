package com.ddd4.dropit.presentation.ui.splash

import androidx.lifecycle.viewModelScope
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.usecase.SetSectionUseCase
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class SplashViewModel(
    private val setSectionUseCase: SetSectionUseCase
): BaseViewModel() {

    val navigateToMain = SingleLiveEvent<Long>()

    init {
        setSectionFromJson()
    }

    private fun setSectionFromJson() {
        viewModelScope.launch {
            when (val result = setSectionUseCase()) {
                is Result.Success -> navigateToMain.call()
                is Result.Error -> Timber.d(result.exception)
            }
        }
    }
}