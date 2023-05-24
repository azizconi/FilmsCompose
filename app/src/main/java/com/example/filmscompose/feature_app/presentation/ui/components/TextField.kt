package com.example.filmscompose.feature_app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmscompose.feature_app.presentation.ui.theme.TextFieldBackgroundColor

@Composable
fun GenericTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String,
    singleLine: Boolean,

    keyBoardIconButton: () -> Unit,
    visualTransformation: () -> VisualTransformation,
    keyboardOptions: () -> KeyboardOptions,
    fontSizeForInputText: Int = 14,

    alignmentText: Alignment.Vertical = Alignment.CenterVertically,
    enabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(32),
    textPadding: Dp = 0.dp
) {


    Column(
        modifier = Modifier
            .padding(bottom = 16.dp)
    ) {


        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier

                .then(modifier),
            singleLine = singleLine,
            cursorBrush = SolidColor(MaterialTheme.colors.primary),
            textStyle = LocalTextStyle.current.copy(
                color = Color.Black,
                fontSize = fontSizeForInputText.sp,
            ),
            keyboardOptions = keyboardOptions(),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyBoardIconButton()
                }
            ),
            visualTransformation = visualTransformation(),
            enabled = enabled,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .clip(shape)
                        .background(TextFieldBackgroundColor),
                    verticalAlignment = alignmentText,
                ) {
                    Box(
                        Modifier
                            .weight(1f)
                            .padding(horizontal = 13.dp)
                            .padding(vertical = textPadding),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty())
                            Text(
                                text = placeholder,
                                style = LocalTextStyle.current.copy(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight(400),
                                ),
                                color = Color.Gray.copy(0.6f)
                            )
                        innerTextField()
                    }
                }
            }
        )
    }
}