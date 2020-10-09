package com.sample.camerax.ui.main

import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class TextAnalyzer(private val result: (String) -> Unit) : BaseAnalyzer<Text>() {

    private val recognizer = TextRecognition.getClient()

    override fun processImage(image: InputImage): Flow<Text> {
        return flow {
            emit(recognizer.process(image).await())
        }
    }

    override fun onSuccess(imageResult: Text) {
        result.invoke(imageResult.text)
    }

    override fun onFailure(exception: Throwable) {
        Log.e("TextAnalyzer", exception.message, exception)
    }

}