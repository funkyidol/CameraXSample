package com.sample.camerax.ui.main

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

abstract class BaseAnalyzer<T> : ImageAnalysis.Analyzer {

    @ExperimentalCoroutinesApi
    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image

        if (mediaImage != null) {
            runBlocking {
                val image =
                    InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
                processImage(image)
                    .catch { e ->
                        onFailure(e)
                        imageProxy.close()
                    }
                    .collect {
                        onSuccess(it)
                        imageProxy.close()
                    }
            }
        } else {
            imageProxy.close()
        }
    }

    abstract fun processImage(image: InputImage): Flow<T>

    abstract fun onSuccess(imageResult: T)

    abstract fun onFailure(exception: Throwable)

}