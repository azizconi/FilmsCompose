package com.example.filmscompose.feature_app.domain.model.film

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmscompose.feature_app.data.remote.response.filmById.FilmResponse
import com.example.filmscompose.feature_app.data.remote.response.search.SearchResult



@Entity(tableName = "favorite")
data class FilmItemModel(
    @PrimaryKey
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

        fun toFilmItemModel(model: FilmResponse): FilmItemModel {
            return FilmItemModel(
                model.imdbID,
                model.Title,
                model.Year,
                model.Type,
                model.Poster,
            )
        }

    }
}
