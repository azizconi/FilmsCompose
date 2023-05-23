package com.example.filmscompose.feature_app.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.filmscompose.feature_app.presentation.ui.screen.main.components.BottomShadowBox
import com.example.filmscompose.feature_app.presentation.ui.theme.PrimaryColor
import com.example.filmscompose.feature_app.utils.advancedShadow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericTopAppBar(
    modifier: Modifier = Modifier
) {


//    Scaffold(
//        topBar = {
//            TopAppBar(title = {
//                IconButton(onClick = { }) {
//                    Icon(
//                        Icons.Default.Search,
//                        contentDescription = null,
//                        tint = Color.Black,
//                        modifier = modifier.size(30.dp)
//                    )
//                }
//            })
//        }
//    ) { contentPadding ->
//        // Screen content
//        contentPadding
//    }


//    TopAppBar(
//        title = {
//            IconButton(onClick = { }) {
//                Icon(
//                    Icons.Default.Search,
//                    contentDescription = null,
//                    tint = Color.Black,
//                    modifier = modifier.size(30.dp)
//                )
//            }
//        },
//        colors = TopAppBarDefaults.mediumTopAppBarColors(
//            Color.White,
//
//        ),
//    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(PrimaryColor)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = { }) {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                tint = Color.Black,
                modifier = modifier.size(30.dp)
            )
        }


    }

}


@Preview
@Composable
fun GenericTopAppBarPreview() {
    GenericTopAppBar()
}