package com.octatech.expertmovie.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.octatech.expertmovie.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
    val favoriteSeries  = movieUseCase.getFavoriteSeries().asLiveData()
}