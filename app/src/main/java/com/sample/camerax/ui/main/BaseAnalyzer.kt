package com.sample.camerax.ui.main

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage

abstract class BaseAnalyzer<T> : ImageAnalysis.Analyzer {

    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image

        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            processImage(image)
                .addOnSuccessListener {
                    onSuccess(it)
                    imageProxy.close()
                }.addOnFailureListener {
                    onFailure(it)
                    imageProxy.close()
                }

        } else {
            imageProxy.close()
        }

    }

    abstract fun processImage(image: InputImage): Task<T>

    abstract fun onSuccess(imageResult: T)

    abstract fun onFailure(exception: Exception)

}