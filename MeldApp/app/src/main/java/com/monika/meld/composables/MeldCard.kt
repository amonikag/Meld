package com.realllydan.meld.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.realllydan.meld.CardShape

@Composable
fun MeldCard (
    text: String,
    color: Color = MaterialTheme.colors.primaryVariant,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null
) {
    Card(
        shape = CardShape,
        backgroundColor = color,
        modifier = Modifier
            .clickable {
                onClick?.invoke()
            }
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onSurface,
            fontSize = 32.sp,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}