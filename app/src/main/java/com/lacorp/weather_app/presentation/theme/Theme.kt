package com.lacorp.weather_app.presentation.theme

import android.app.Activity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import com.lacorp.weather_app.util.WeatherEnum

private val CloudyColorPalette = lightColors(
    primary = Cloudy50,
    primaryVariant = Cloudy70,
    secondary = Cloudy10,
    secondaryVariant = Cloudy30
)

private val SunnyColorPalette = lightColors(
    primary = Sunny50,
    primaryVariant = Sunny70,
    secondary = Sunny10,
    secondaryVariant = Sunny30
)

private val RainyColorPalette = lightColors(
    primary = Rainy50,
    primaryVariant = Rainy70,
    secondary = Rainy10,
    secondaryVariant = Rainy30
)

private val StormyColorPalette = lightColors(
    primary = Stormy50,
    primaryVariant = Stormy70,
    secondary = Stormy10,
    secondaryVariant = Stormy30
)

@Composable
fun WeatherAppTheme(mode: WeatherEnum, content: @Composable () -> Unit) {
    val colors = when (mode) {
        WeatherEnum.CLOUDY -> CloudyColorPalette
        WeatherEnum.RAINY -> RainyColorPalette
        WeatherEnum.SUNNY -> SunnyColorPalette
        WeatherEnum.STORMY -> StormyColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}