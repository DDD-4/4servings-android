package com.ddd4.dropit.presentation.util

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber

fun ImageView.loadUriCenterCrop(uri : Uri) {
    val options = RequestOptions()
    Glide.with(this).load(uri).apply(options.centerCrop()).into(this)
    Timber.d("glide loaded %s", uri.toString())
}

fun ImageView.loadUrlCenterCrop(url : String) {
    val options = RequestOptions()
    Timber.d("loaded %s", url)
    Glide.with(this).load(url).apply(options.centerCrop()).into(this)
}

fun ImageView.loadUri(uri : Uri) {
    Glide.with(this).load(uri).into(this)
}

fun ImageView.loadUrl(url : String?) {
    url?.let {
        Glide.with(this).load(it).into(this)
    }
}

fun ImageView.loadDrawable(drawable: Drawable) {
    Glide.with(this).load(drawable).into(this)
}