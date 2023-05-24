package com.example.filmscompose.feature_app.presentation.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.filmscompose.feature_app.domain.model.film.FilmItemModel
import com.example.filmscompose.feature_app.presentation.ui.components.GenericTopAppBar
import com.example.filmscompose.feature_app.presentation.ui.navigation.Screen
import com.example.filmscompose.feature_app.presentation.ui.screen.main.components.BottomSheetContent
import com.example.filmscompose.feature_app.presentation.ui.screen.main.components.FilmItem
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

    val searchResult = viewModel.searchResult.collectAsLazyPagingItems()

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
                items(searchResult) { item ->
                    item?.let {
                        FilmItem(
                            item = it.let { FilmItemModel.toFilmItemModel(it) },
                            onClick = {
                                navController.navigate(Screen.Film + "/${it.id}")
                            }
                        )
                    }
                }
            }


//            LazyColumn(
//                contentPadding = PaddingValues(top = 20.dp)
//            ) {
//
//                when (val result = viewModel.searchResult.value) {
//                    is Resource.Success -> {
//
//                        result.data?.data?.map {
//                            FilmItemModel.toFilmItemModel(it)
//                        }?.let { list ->
//                            items(searchResult) {
//                                FilmItem(
//                                    item = it,
//                                    onClick = {
//                                        navController.navigate(Screen.Film + "/${it.id}")
//                                    }
//                                )
//                            }
//                        }
//                    }
//
//                    is Resource.Error -> {
//                        item {
//                            Box(
//                                Modifier.fillMaxSize(),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                IconButton(onClick = { viewModel.searchResult(search) }) {
//                                    Icon(Icons.Default.Refresh, contentDescription = "Refresh icon")
//                                }
//                            }
//                        }
//                    }
//
//                    is Resource.Loading -> {
//                        item {
//                            DialogProgressBar()
//                        }
//                    }
//
//                    is Resource.Inactive -> {
//
//                    }
//                }
//
//
//            }
        }
    }

}