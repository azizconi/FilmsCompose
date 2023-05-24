package com.example.filmscompose.feature_app.di

import com.example.filmscompose.feature_app.data.remote.FilmApi
import com.example.filmscompose.feature_app.data.repository.FilmRepositoryImpl
import com.example.filmscompose.feature_app.domain.repository.FilmRepository
import com.example.filmscompose.feature_app.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): FilmApi {
        return retrofit.create(FilmApi::class.java)
    }


    @Singleton
    @Provides
    fun provideFilmRepository(api: FilmApi): FilmRepository = FilmRepositoryImpl(api)
}