package com.example.filmscompose.feature_app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filmscompose.feature_app.domain.model.film.FilmItemModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFilmToFavorite(filmItemModel: FilmItemModel)

    @Query("select * from favorite where `id` = :id")
    fun getFavoriteById(id: String): Flow<FilmItemModel?>

    @Delete
    suspend fun deleteFilm(filmItemModel: FilmItemModel)
}