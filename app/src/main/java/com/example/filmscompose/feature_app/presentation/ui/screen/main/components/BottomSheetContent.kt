package com.example.filmscompose.feature_app.presentation.ui.screen.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmscompose.feature_app.presentation.ui.components.GenericTextField
import com.example.filmscompose.feature_app.presentation.ui.components.VerticalSpacer

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomSheetContent(
    searchText: String,
    onApplyClick: (String) -> Unit
) {


    var title by remember(searchText) { mutableStateOf(searchText) }
    val keyboard = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer()
        Text(
            text = "Search",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

        VerticalSpacer()


        Column(
            Modifier.padding(horizontal = 16.dp)
        ) {
            GenericTextField(
                value = title,
                onValueChange = {
                    title = it
                },
                singleLine = true,
                placeholder = "title",
                keyBoardIconButton = {
                    keyboard?.hide()
                    if (title.isNotEmpty()) onApplyClick(title)
                },
                visualTransformation = {
                    VisualTransformation.None
                },
                keyboardOptions = {
                    KeyboardOptions(
                        imeAction = ImeAction.Search,
                        keyboardType = KeyboardType.Text
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            )

            OutlinedButton(
                onClick = {onApplyClick(title)},
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(32)
            ) {
                Text(text = "Apply")
            }

            VerticalSpacer(30.dp)
        }
    }


}