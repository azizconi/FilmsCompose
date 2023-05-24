package com.example.filmscompose.feature_app.presentation.ui.screen.film

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmscompose.feature_app.data.remote.response.filmById.FilmResponse
import com.example.filmscompose.feature_app.domain.model.film.FilmItemModel
import com.example.filmscompose.feature_app.domain.use_case.FavoriteUseCase
import com.example.filmscompose.feature_app.domain.use_case.FilmByIdUseCase
import com.example.filmscompose.feature_app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val filmUseCase: FilmByIdUseCase,
    private val favoriteUseCase: FavoriteUseCase
): ViewModel() {

    private val _filmResult = mutableStateOf<Resource<FilmResponse>>(Resource.Inactive())
    val filmResult: State<Resource<FilmResponse>> = _filmResult

    var isFavoriteFilm = mutableStateOf(false)

    fun getFilm(id: String) {
        viewModelScope.launch {
            launch {
                filmUseCase(id).onEach {
                    _filmResult.value = it
                }.launchIn(this)
            }

            launch {
                favoriteUseCase.getFilm(id).collectLatest {
                    isFavoriteFilm.value = it?.id != null
                }
            }
        }
    }

    fun addFilmToFavorite(filmItemModel: FilmItemModel) {
        viewModelScope.launch {
            favoriteUseCase.saveFilm(filmItemModel)
        }
    }

    fun deleteFilmToFavorite(filmItemModel: FilmItemModel) {
        viewModelScope.launch {
            favoriteUseCase.deleteFilm(filmItemModel)
        }
    }



}