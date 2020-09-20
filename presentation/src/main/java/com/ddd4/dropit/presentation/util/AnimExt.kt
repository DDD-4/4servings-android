package com.ddd4.dropit.presentation.util

import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun Button.showButton() {
    this.animate().translationY(0f)
}

fun Button.hideButton() {
    this.animate().translationY(300f)
}

fun FloatingActionButton.showButton(){
    this.animate().translationY(0f)
}

fun FloatingActionButton.hideButton(){
    this.animate().translationY(300f)
}