package com.sample.camerax.ui.main

import com.google.mlkit.vision.text.Text

class InstantTextViewModel : BaseAnalyzerViewModel<Text>() {
    private val textAnalyzer = TextAnalyzer() {
        resultLiveData.postValue(it)
    }

    override fun getAnalyzer(): BaseAnalyzer<Text> {
        return textAnalyzer
    }
}