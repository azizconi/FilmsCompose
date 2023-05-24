package com.example.filmscompose.feature_app.domain.use_case

import com.example.filmscompose.feature_app.domain.repository.FilmRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: FilmRepository
) {

    suspend operator fun invoke(text: String, page: Int) =
        repository.search(text, page)



}