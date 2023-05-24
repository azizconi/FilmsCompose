package com.example.filmscompose.feature_app.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.filmscompose.feature_app.utils.percentageWidthSize

@Composable
fun DialogProgressBar(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {}
) {

    val context = LocalContext.current

    val size = context.percentageWidthSize(25)

    AlertDialog(
        onDismissRequest = onDismissRequest,
        buttons = {
            Column(
                modifier = modifier
                    .padding(16.dp),
            ) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }


            }
        },
        modifier = modifier
            .size(size),
        shape = RoundedCornerShape(8.dp),
    )

}