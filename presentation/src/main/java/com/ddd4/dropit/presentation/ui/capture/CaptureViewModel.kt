package com.ddd4.dropit.presentation.ui.capture

import androidx.hilt.lifecycle.ViewModelInject
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.util.SingleLiveEvent

class CaptureViewModel @ViewModelInject constructor() : BaseViewModel() {

    val captureComplete = SingleLiveEvent<String>()
    val captureClick = SingleLiveEvent<Void>()

    fun onCaptureClicked() {
        captureClick.call()
    }

    fun setCapturedImage(imageUri: String) {
        captureComplete.value = imageUri
    }
}