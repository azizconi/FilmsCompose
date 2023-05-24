package com.example.filmscompose.feature_app.domain.model.film

import com.example.filmscompose.feature_app.data.remote.response.search.SearchResult


data class FilmItemModel(
    val id: String,
    val title: String,
    val year: String,
    val type: String,
    val image: String,
    var isFavorite: Boolean = false
) {
    companion object {
        fun toFilmItemModel(model: SearchResult): FilmItemModel {
            return FilmItemModel(
                model.id,
                model.title,
                model.year,
                model.type,
                model.poster,
            )
        }
    }
}
