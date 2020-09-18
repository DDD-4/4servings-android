package com.ddd4.dropit.presentation.ui.capture

import android.view.TextureView
import androidx.databinding.BindingAdapter

object CaptureBinding {

    @JvmStatic
    @BindingAdapter("listener")
    fun setTextureListener(view: TextureView, listener: TextureView.SurfaceTextureListener) {
        view.surfaceTextureListener = listener
    }
}