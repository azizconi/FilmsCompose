package com.example.filmscompose.feature_app.data.remote

import com.example.filmscompose.feature_app.data.remote.response.filmById.FilmResponse
import com.example.filmscompose.feature_app.data.remote.response.search.SearchResponse
import com.example.filmscompose.feature_app.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmApi {


    @GET("/")
    suspend fun getSearchResult(
        @Query("s") text: String,
        @Query("page") page: Int,
        @Query("apikey") apiKey: String = Constants.TOKEN
    ): Response<SearchResponse>

    @GET("/")
    suspend fun getFilmById(
        @Query("i") i: String,
        @Query("apikey") apiKey: String = Constants.TOKEN
    ): Response<FilmResponse>


}