package com.sample.camerax.ui.main

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition

class TextAnalyzer(private val result: (String) -> Unit) : BaseAnalyzer<Text>(){

    private val recognizer = TextRecognition.getClient()
    override fun onFailure(exception: Exception) {
        Log.e("TextAnalyzer",exception.message, exception)
    }

    override fun onSuccess(imageResult: Text) {
        result.invoke(imageResult.text)
    }

    override fun processImage(image: InputImage): Task<Text> {
        return recognizer.process(image)
    }

}