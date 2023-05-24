package com.example.filmscompose.feature_app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmscompose.feature_app.presentation.ui.theme.PrimaryColor

@Composable
fun GenericTopAppBar(
    modifier: Modifier = Modifier,
    header: String,
    type: String = "main",
    onClickToIcon: () -> Unit
) {


    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(PrimaryColor)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {


        IconButton(onClick = onClickToIcon) {
            Icon(
                if (type == "main") Icons.Default.Search else Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.Black,
                modifier = modifier.size(30.dp)
            )
        }


        Box(
            modifier = modifier
                .weight(1f)
                .offset(x = (-20).dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = header,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }


    }

}


@Preview
@Composable
fun GenericTopAppBarPreview() {
    GenericTopAppBar(onClickToIcon = {}, header = "Movies")
}