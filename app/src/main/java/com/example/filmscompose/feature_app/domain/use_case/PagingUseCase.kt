package com.example.filmscompose.feature_app.domain.use_case

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.filmscompose.feature_app.data.remote.response.search.SearchResponse
import com.example.filmscompose.feature_app.data.remote.response.search.SearchResult
import retrofit2.HttpException
import retrofit2.Response

class PagingUseCase(
    private val api: suspend (page: Int) -> Response<SearchResponse>
) : PagingSource<Int, SearchResult>() {
    override fun getRefreshKey(state: PagingState<Int, SearchResult>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.minus(1) ?: page.nextKey?.plus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResult> {
        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize.coerceAtMost(10)

        val response = api(page)

        return if (response.isSuccessful) {
            val data = checkNotNull(response.body()).data
            val nextKey = try {
                if (data.size < pageSize) null else page + 1
            } catch (e: Exception) {
                null
            }
            val prevKey = try {
                if (page == 1) null else page - 1
            } catch (e: Exception) {
                null
            }
            try {
                LoadResult.Page(data, prevKey, nextKey)
            } catch (e: Exception) {
                LoadResult.Error(HttpException(response))
            }
        } else {
            LoadResult.Error(HttpException(response))
        }
    }


}