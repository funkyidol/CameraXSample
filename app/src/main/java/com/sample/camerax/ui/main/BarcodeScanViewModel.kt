package com.sample.camerax.ui.main

import com.google.mlkit.vision.barcode.Barcode

class BarcodeScanViewModel : BaseAnalyzerViewModel<MutableList<Barcode>>() {
    private val barcodeAnalyzer = BarcodeAnalyzer() {
        resultLiveData.value = it
    }

    override fun getAnalyzer(): BaseAnalyzer<MutableList<Barcode>> {
        return barcodeAnalyzer
    }

}