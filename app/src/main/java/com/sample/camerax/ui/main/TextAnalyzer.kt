package com.sample.camerax.ui.main

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition

class TextAnalyzer(private val result: (String) -> Unit) : ImageAnalysis.Analyzer {

    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        val recognizer = TextRecognition.getClient()
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            recognizer.process(image)
                .addOnSuccessListener { text ->
                    Log.d(TAG, "Text ${text.text}")
                    result.invoke(text.text)
                    imageProxy.close()
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Mlkit processing Failed", e)
                    imageProxy.close()
                }
        } else {
            imageProxy.close()
        }
    }

    companion object {
        private const val TAG: String = "TextAnalyzer"
    }
}