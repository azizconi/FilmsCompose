package com.example.filmscompose.feature_app.presentation.ui.screen.main

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.filmscompose.feature_app.data.remote.response.search.SearchResponse
import com.example.filmscompose.feature_app.data.remote.response.search.SearchResult
import com.example.filmscompose.feature_app.domain.use_case.PagingUseCase
import com.example.filmscompose.feature_app.domain.use_case.SearchUseCase
import com.example.filmscompose.feature_app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    //    private val _searchResult = mutableStateOf<Resource<SearchResponse>>(Resource.Inactive())
//    val searchResult: State<Resource<SearchResponse>> = _searchResult
    private val _searchResult = MutableStateFlow<PagingData<SearchResult>>(PagingData.empty())
    val searchResult = _searchResult.asStateFlow()


    fun getSearchResult(
        searchText: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _getSearchResult(searchText).stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                PagingData.empty()
            ).cachedIn(this).collect {
                _searchResult.value = it
            }
        }
    }

    private fun _getSearchResult(text: String) = Pager(
        PagingConfig(
            pageSize = 10,
            prefetchDistance = 2
        ),
    ) {
        PagingUseCase {
            Log.e("TAG", "_getSearchResult: ${it}", )
            searchUseCase(text, it)
        }
    }.flow.cachedIn(viewModelScope)


    /*val films: Flow<PagingData<SearchResult>> = */


}