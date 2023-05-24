package com.example.filmscompose.feature_app.domain.use_case

import com.example.filmscompose.feature_app.domain.repository.FilmRepository
import javax.inject.Inject

class FilmByIdUseCase @Inject constructor(private val filmRepository: FilmRepository) {


    operator fun invoke(id: String) = filmRepository.getFilmById(id)

}