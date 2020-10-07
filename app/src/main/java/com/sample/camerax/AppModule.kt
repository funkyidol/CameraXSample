package com.sample.camerax

import com.sample.camerax.ui.main.BarcodeScanViewModel
import com.sample.camerax.ui.main.CameraImageAnalysis
import com.sample.camerax.ui.main.InstantTextViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        CameraImageAnalysis()
    }
    viewModel { InstantTextViewModel() }
    viewModel { BarcodeScanViewModel() }
}