package com.example.filmscompose.feature_app.presentation.ui.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.filmscompose.feature_app.utils.Resource


@ExperimentalFoundationApi
fun <T : Any> LazyListScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyItemScope.(value: T?) -> Unit,
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems[index])
    }
}


@Composable
fun <T : Any> collectAsLazyPagingObserveItems(lazyItems: LazyPagingItems<T>): Resource<LazyPagingItems<T>> {

    return when (lazyItems.loadState.refresh) {
        is LoadState.Loading -> Resource.Loading(lazyItems)
        is LoadState.NotLoading -> {
            if (lazyItems.itemSnapshotList.items.isNotEmpty()) {
                Resource.Success(lazyItems)
            } else {
                Resource.Inactive()
            }
        }
        is LoadState.Error -> Resource.Error("Error", lazyItems)
    }

}

@Composable
fun <T : Any> collectAsLazyPagingObserve(lazyItems: LazyPagingItems<T>): LazyPagingItems<T>? {

    return if (lazyItems.loadState.refresh is LoadState.NotLoading) {
        lazyItems
    } else {
        null
    }

}


