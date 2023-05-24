package com.example.filmscompose.feature_app.domain.repository

import com.example.filmscompose.feature_app.data.remote.response.filmById.FilmResponse
import com.example.filmscompose.feature_app.data.remote.response.search.SearchResponse
import com.example.filmscompose.feature_app.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface FilmRepository {

    suspend fun search(text: String, page: Int): Response<SearchResponse>
    fun getFilmById(i: String): Flow<Resource<FilmResponse>>
}