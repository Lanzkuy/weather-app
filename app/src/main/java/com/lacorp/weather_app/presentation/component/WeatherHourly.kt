package com.lacorp.weather_app.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WeatherHourly(
    modifier: Modifier,
    painterResourceId: Int,
    time: String,
    temperature: String,
    textStyle: TextStyle,
    color: Color,
    width: Dp,
    height: Dp,
    space: Dp,
) {
    Column(
        modifier = modifier.padding(
            top = 10.dp,
            start = 16.dp,
            bottom = 16.dp,
            end = 10.dp
        )
    ) {
        Text(
            modifier = modifier
                .align(Alignment.CenterHorizontally),
            text = time,
            color = color,
            style = textStyle,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = modifier.padding(space))
        Icon(
            modifier = modifier
                .size(width, height)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = painterResourceId),
            tint = color,
            contentDescription = null,
        )
        Spacer(modifier = modifier.padding(space))
        Text(
            modifier = modifier
                .align(Alignment.CenterHorizontally),
            text = "${temperature}°",
            color = color,
            style = textStyle,
            fontWeight = FontWeight.Light
        )
    }
}