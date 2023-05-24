package com.example.filmscompose.feature_app.presentation.ui.screen.film

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.filmscompose.feature_app.domain.model.film.FilmItemModel
import com.example.filmscompose.feature_app.presentation.ui.components.DialogProgressBar
import com.example.filmscompose.feature_app.presentation.ui.components.GenericTextField
import com.example.filmscompose.feature_app.presentation.ui.components.GenericTopAppBar
import com.example.filmscompose.feature_app.presentation.ui.components.HorizontalSpacer
import com.example.filmscompose.feature_app.presentation.ui.components.VerticalSpacer
import com.example.filmscompose.feature_app.presentation.ui.theme.TextFieldBackgroundColor
import com.example.filmscompose.feature_app.utils.Resource
import kotlinx.coroutines.delay

@Composable
fun FilmScreen(
    navController: NavHostController,
    viewModel: FilmViewModel = hiltViewModel(),
    id: String
) {

    LaunchedEffect(key1 = Unit) { viewModel.getFilm(id) }

    var comment by remember { mutableStateOf("") }
    var isCommentSend by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(key1 = isCommentSend) {
        if (isCommentSend) {
            delay(2000)
            comment = ""
            isCommentSend = false
            Toast.makeText(context, "Send", Toast.LENGTH_SHORT).show()
        }
    }

    Column {
        GenericTopAppBar(header = "Movie info", type = "film-screen") {
            navController.popBackStack()
        }


        Column {
            VerticalSpacer()


            when (val result = viewModel.filmResult.value) {
                is Resource.Success -> {
                    result.data?.let {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(16.dp)
                                .shadow(3.dp, shape = RoundedCornerShape(12.dp))
                        ) {
                            Row(
                                Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White)
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(it.Poster),
                                    contentDescription = "film poster",
                                    modifier = Modifier.width(150.dp)
                                )

                                Column(
                                    Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Text(
                                            text = it.Title,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(text = it.Year)

                                        VerticalSpacer(20.dp)

                                        Text(text = it.Type)
                                    }


                                    Row {
                                        Box(
                                            modifier = Modifier
                                                .width(60.dp)
                                                .height(30.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(TextFieldBackgroundColor),
                                            contentAlignment = Alignment.Center

                                        ) {
                                            Text(
                                                text = "imdb".uppercase(),
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        HorizontalSpacer()

                                        Box(
                                            modifier = Modifier
                                                .size(30.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(TextFieldBackgroundColor)
                                                .clickable {
                                                    if (!viewModel.isFavoriteFilm.value) {
                                                        viewModel.addFilmToFavorite(
                                                            FilmItemModel.toFilmItemModel(it)
                                                        )
                                                    } else {
                                                        viewModel.deleteFilmToFavorite(
                                                            FilmItemModel.toFilmItemModel(it)
                                                        )
                                                    }
                                                },
                                            contentAlignment = Alignment.Center,
                                        ) {
                                            Icon(
                                                if (!viewModel.isFavoriteFilm.value) Icons.Default.FavoriteBorder
                                                else Icons.Default.Favorite,
                                                contentDescription = "favorite-icon",
                                                modifier = Modifier.size(24.dp),
                                                tint = Color.Red
                                            )
                                        }
                                    }


                                }
                            }

                        }



                        Column {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                Text(text = "Comments".uppercase())
                            }
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .padding(16.dp)
                                    .shadow(3.dp, shape = RoundedCornerShape(12.dp))
                            ) {

                                Column(
                                    Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color.White)
                                        .padding(16.dp),
                                ) {

                                    GenericTextField(
                                        value = comment,
                                        onValueChange = {
                                            comment = it
                                        },
                                        singleLine = false,
                                        placeholder = "Comment text",
                                        keyBoardIconButton = {},
                                        visualTransformation = {
                                            VisualTransformation.None
                                        },
                                        keyboardOptions = {
                                            KeyboardOptions(
                                                imeAction = ImeAction.Go,
                                                keyboardType = KeyboardType.Text
                                            )
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(100.dp),
                                        shape = RoundedCornerShape(16),
                                        alignmentText = Alignment.Top,
                                        textPadding = 4.dp
                                    )


                                    OutlinedButton(
                                        onClick = {
                                            if (comment.isNotEmpty()) {
                                                isCommentSend = true
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Input is empty",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(48.dp),
                                        shape = RoundedCornerShape(32)
                                    ) {
                                        androidx.compose.material3.Text(text = "Leave comment")
                                    }


                                }
                            }
                        }
                    }
                }

                is Resource.Error -> {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { viewModel.getFilm(id) }) {
                            Icon(Icons.Default.Refresh, contentDescription = "Refresh icon")
                        }
                    }
                }

                is Resource.Inactive -> {

                }

                is Resource.Loading -> {
                    DialogProgressBar()
                }

            }


        }
    }
    if (isCommentSend) {
        DialogProgressBar()
    }

}