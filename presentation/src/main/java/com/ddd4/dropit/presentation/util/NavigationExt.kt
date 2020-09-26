package com.ddd4.dropit.presentation.util

import android.app.Activity
import androidx.navigation.NavController

fun NavController.navigateUpOrFinish(activity: Activity): Boolean {
    return if (navigateUp()) {
        true
    } else {
        activity.finish()
        true
    }
}