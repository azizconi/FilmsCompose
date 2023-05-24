package com.example.filmscompose.feature_app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.filmscompose.feature_app.data.local.dao.FilmsDao
import com.example.filmscompose.feature_app.domain.model.film.FilmItemModel

@Database(
    entities = [FilmItemModel::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmsDao(): FilmsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "films_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }

        }
    }
}