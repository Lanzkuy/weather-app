package com.lacorp.weather_app.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WeatherDaily(
    modifier: Modifier,
    painterResourceId: Int,
    day: String,
    highTemp: String,
    lowTemp: String,
    textStyle: TextStyle,
    color: Color,
    width: Dp,
    height: Dp,
    space: Dp,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 12.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
            text = day,
            color = color,
            style = textStyle,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = modifier.padding(space))
        Icon(
            modifier = modifier
                .size(width, height)
                .align(Alignment.CenterVertically)
                .weight(1f),
            painter = painterResource(id = painterResourceId),
            tint = color,
            contentDescription = null,
        )
        Spacer(modifier = modifier.padding(space))
        Text(
            modifier = modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
            text = "$highTemp° / $lowTemp°",
            textAlign = TextAlign.End,
            color = color,
            style = textStyle,
            fontWeight = FontWeight.Light
        )
    }
}