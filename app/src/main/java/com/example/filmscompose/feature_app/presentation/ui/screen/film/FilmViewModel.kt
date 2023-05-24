package com.example.filmscompose.feature_app.presentation.ui.screen.film

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmscompose.feature_app.data.remote.response.filmById.FilmResponse
import com.example.filmscompose.feature_app.domain.use_case.FilmByIdUseCase
import com.example.filmscompose.feature_app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val filmUseCase: FilmByIdUseCase
): ViewModel() {

    private val _filmResult = mutableStateOf<Resource<FilmResponse>>(Resource.Inactive())
    val filmResult: State<Resource<FilmResponse>> = _filmResult

    fun getFilm(id: String) {
        viewModelScope.launch {
            filmUseCase(id).onEach {
                _filmResult.value = it
            }.launchIn(this)
        }
    }

}