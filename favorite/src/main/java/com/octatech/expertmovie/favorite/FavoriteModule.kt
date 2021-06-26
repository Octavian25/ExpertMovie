package com.octatech.expertmovie.favorite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val FavoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}