package com.example.filmscompose.feature_app.presentation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(dp: Dp = 20.dp) {
    Spacer(modifier = Modifier.height(dp))
}

@Composable
fun HorizontalSpacer(dp: Dp = 20.dp) {
    Spacer(modifier = Modifier.width(dp))
}

