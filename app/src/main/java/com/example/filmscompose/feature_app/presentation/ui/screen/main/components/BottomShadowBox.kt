package com.example.filmscompose.feature_app.presentation.ui.screen.main.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun BottomShadowBox(
    content: @Composable () -> Unit
) {

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 4.dp)
        ) {
            content()
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(
                color = Color.Black,
                alpha = 0.1f,
                topLeft = Offset(0f, size.height - 4.dp.toPx()),
                size = Size(size.width, 4.dp.toPx())
            )
        }
    }
}