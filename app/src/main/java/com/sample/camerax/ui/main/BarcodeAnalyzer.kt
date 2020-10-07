package com.sample.camerax.ui.main

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class BarcodeAnalyzer(private val result: (String) -> Unit) : BaseAnalyzer<MutableList<Barcode>>() {
    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()
    val scanner = BarcodeScanning.getClient(options)


    override fun processImage(image: InputImage): Task<MutableList<Barcode>> {
        return scanner.process(image)
    }

    override fun onFailure(exception: Exception) {
        Log.e("BarcodeAnalyzer",exception.message, exception)
    }

    override fun onSuccess(imageResult: MutableList<Barcode>) {
        if (!imageResult.isNullOrEmpty()) {
            imageResult[0].displayValue?.let { this.result.invoke(it) }
        }
    }

}