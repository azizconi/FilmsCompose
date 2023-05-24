package com.example.filmscompose.feature_app.data.remote.response.search

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val id: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String
)
