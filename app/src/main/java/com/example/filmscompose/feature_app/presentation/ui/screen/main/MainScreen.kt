package com.example.filmscompose.feature_app.presentation.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.filmscompose.feature_app.domain.model.film.FilmItemModel
import com.example.filmscompose.feature_app.presentation.ui.components.DialogProgressBar
import com.example.filmscompose.feature_app.presentation.ui.components.GenericTopAppBar
import com.example.filmscompose.feature_app.presentation.ui.components.collectAsLazyPagingObserveItems
import com.example.filmscompose.feature_app.presentation.ui.navigation.Screen
import com.example.filmscompose.feature_app.presentation.ui.screen.main.components.BottomSheetContent
import com.example.filmscompose.feature_app.presentation.ui.screen.main.components.FilmItem
import com.example.filmscompose.feature_app.utils.Resource
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    val searchResult =
        collectAsLazyPagingObserveItems(viewModel.searchResult.collectAsLazyPagingItems())

    var search by rememberSaveable { mutableStateOf("") }

    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetContent(
                searchText = search,
                onApplyClick = { searchText ->
                    search = searchText
                    viewModel.getSearchResult(searchText)
                    scope.launch {
                        bottomSheetState.hide()
                    }
                }
            )
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetBackgroundColor = Color.White
    ) {
        Column {
            GenericTopAppBar(
                header = if (search.isEmpty()) "Movies" else search,
                onClickToIcon = {
                    scope.launch {
                        bottomSheetState.show()
                    }
                }
            )

            LazyColumn(contentPadding = PaddingValues(top = 20.dp)) {
                when (searchResult) {
                    is Resource.Error -> {

                    }

                    is Resource.Inactive -> {
                        item {
                            Box(
                                Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "SET THE SEARCH PARAMETERS")
                            }
                        }
                    }

                    is Resource.Loading -> {
                        item {
                            DialogProgressBar()
                        }
                    }

                    is Resource.Success -> {
                        val result = searchResult.data
                        result?.let { items ->
                            items(items) { item ->
                                item?.let {
                                    val isFavorite =
                                        viewModel.checkFilmToFavorite(it.id).collectAsState(null)


                                    val film = it.let {
                                        FilmItemModel.toFilmItemModel(
                                            it
                                        )
                                    }

                                    FilmItem(
                                        item = film,
                                        onClick = {
                                            navController.navigate(Screen.Film + "/${it.id}")
                                        },
                                        isFavorite = isFavorite.value != null,
                                        onFavoriteClick = {
                                            if (isFavorite.value != null) {
                                                viewModel.deleteFilmToFavorite(film)
                                            } else {
                                                viewModel.addFilmToFavorite(film)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

            }


        }
    }

}