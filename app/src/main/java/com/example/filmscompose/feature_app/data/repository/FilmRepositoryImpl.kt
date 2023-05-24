package com.example.filmscompose.feature_app.data.repository

import com.example.filmscompose.feature_app.data.remote.FilmApi
import com.example.filmscompose.feature_app.data.remote.response.filmById.FilmResponse
import com.example.filmscompose.feature_app.data.remote.response.search.SearchResponse
import com.example.filmscompose.feature_app.domain.repository.FilmRepository
import com.example.filmscompose.feature_app.utils.Resource
import com.example.filmscompose.feature_app.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class FilmRepositoryImpl(
    private val api: FilmApi
): FilmRepository {
    override suspend fun search(text: String, page: Int): Response<SearchResponse> = api.getSearchResult(text, page)


    override fun getFilmById(i: String): Flow<Resource<FilmResponse>> = safeApiCall {
        api.getFilmById(i = i)
    }
}