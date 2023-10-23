package com.lacorp.weather_app.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp

@Composable
fun TextIcon(
    modifier: Modifier,
    imageVector: ImageVector,
    text: String,
    textStyle: TextStyle,
    color: Color,
    width: Dp,
    height: Dp,
    space: Dp,
    isFill: Boolean
) {
    Row(
        modifier = modifier.then(if (isFill) Modifier.fillMaxWidth() else Modifier),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = CenterVertically
    ) {
        Icon(
            modifier = modifier
                .size(width, height),
            imageVector = imageVector,
            tint = color,
            contentDescription = null,
        )
        Spacer(modifier = modifier.padding(space))
        Text(
            text = text,
            color = color,
            style = textStyle,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun TextIcon(
    modifier: Modifier,
    painterResourceId: Int,
    text: String,
    textStyle: TextStyle,
    color: Color,
    width: Dp,
    height: Dp,
    space: Dp,
    isFill: Boolean
) {
    Row(
        modifier = modifier.then(if (isFill) Modifier.fillMaxWidth() else modifier),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = CenterVertically
    ) {
        Icon(
            modifier = modifier
                .size(width, height),
            painter = painterResource(id = painterResourceId),
            tint = color,
            contentDescription = null,
        )
        Spacer(modifier = modifier.padding(space))
        Text(
            text = text,
            color = color,
            style = textStyle,
            fontWeight = FontWeight.Light
        )
    }
}