package com.sample.camerax.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.concurrent.Executor

class InstantTextViewModel : ViewModel(), KoinComponent {

    private val cameraImageAnalysis: CameraImageAnalysis by inject()

    val resultLiveData = MutableLiveData<String>()

    fun startImageAnalysis(cameraExecutor: Executor) {
        cameraImageAnalysis.imageAnalysis.setAnalyzer(cameraExecutor, TextAnalyzer() {
            resultLiveData.value = it
        })
    }

    fun stopImageAnalysis() {
        cameraImageAnalysis.imageAnalysis.clearAnalyzer()
    }

}