package com.example.filmscompose.feature_app.domain.use_case

import com.example.filmscompose.feature_app.data.local.dao.FilmsDao
import com.example.filmscompose.feature_app.domain.model.film.FilmItemModel
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(
    private val dao: FilmsDao
) {


    suspend fun saveFilm(filmItemModel: FilmItemModel) = dao.addFilmToFavorite(filmItemModel)

    fun getFilm(id: String) = dao.getFavoriteById(id)
    suspend fun deleteFilm(filmItemModel: FilmItemModel) = dao.deleteFilm(filmItemModel)

}