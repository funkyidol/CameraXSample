package com.sample.camerax

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class PermissionsHelper(private val fragment: Fragment) {
    fun hasCameraPermission(): Boolean {
        val permissionCheckResult = ContextCompat.checkSelfPermission(
            fragment.requireContext(),
            Manifest.permission.CAMERA
        )
        return permissionCheckResult == PackageManager.PERMISSION_GRANTED
    }

    fun requestCameraPermission() {
        fragment.requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CODE)
    }

    fun resultGranted(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ): Boolean {
        if (requestCode != REQUEST_CODE) {
            return false
        }
        if (grantResults.size < 1) {
            return false
        }
        if (permissions[0] != Manifest.permission.CAMERA) {
            return false
        }
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        requestCameraPermission()
        return false
    }

    companion object {
        private const val REQUEST_CODE = 10
    }

}