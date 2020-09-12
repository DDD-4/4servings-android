package com.ddd4.dropit.presentation.util.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_DENIED
import androidx.core.content.ContextCompat

class PermissionHelperImpl(private val context: Context): PermissionHelper {

    override fun isCapturePermissionState(): Boolean {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PERMISSION_DENIED) {
            return false
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PERMISSION_DENIED) {
            return false
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PERMISSION_DENIED) {
            return false
        }
        return true
    }
}