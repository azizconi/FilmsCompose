package com.example.filmscompose.feature_app.data.remote.response.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("Search") val data: List<SearchResult>,
    @SerializedName("totalResults") val totalResults: Int,
)
