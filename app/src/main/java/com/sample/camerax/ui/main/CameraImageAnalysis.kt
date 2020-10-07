package com.sample.camerax.ui.main

import android.util.Size
import androidx.camera.core.ImageAnalysis

class CameraImageAnalysis {
    val imageAnalysis: ImageAnalysis = ImageAnalysis.Builder()
        .setTargetResolution(Size(1280, 720))
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build()

}