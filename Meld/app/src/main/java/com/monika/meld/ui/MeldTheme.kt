package com.realllydan.meld

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val meld_accent = Color(0xFF11C21F)
val meld_white = Color.White
val meld_purple_400 = Color(0xFFA32E8A)
val meld_purple_700 = Color(0xFF414B50)
val meld_purple_800 = Color(0xFF2A2F32)
val meld_purple_900 = Color(0xFF1C1E1F)

val meldColors = lightColors(
    primary = meld_purple_800,
    secondary = meld_accent,
    surface = meld_purple_900,
    onSurface = meld_white,
    primaryVariant = meld_purple_700
)

val CardShape = RoundedCornerShape(
    topStart = 15.dp,
    topEnd = 0.dp,
    bottomStart = 0.dp,
    bottomEnd = 15.dp
)

@Composable
fun MeldTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = meldColors, typography = meldTypography) {
        content()
    }
}

