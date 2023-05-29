package com.lacorp.weather_app.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.lacorp.weather_app.util.WeatherEnum

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val CloudyColorPalette = lightColors(
    primary = Cloudy50,
    primaryVariant = Cloudy70,
    secondary = Cloudy30,
    secondaryVariant = Cloudy90
)

private val SunnyColorPalette = lightColors(
    primary = Sunny50,
    primaryVariant = Sunny70,
    secondary = Sunny30,
    secondaryVariant = Sunny90
)

private val RainyColorPalette = lightColors(
    primary = Rainy50,
    primaryVariant = Rainy70,
    secondary = Rainy30,
    secondaryVariant = Rainy90
)

private val StormyColorPalette = lightColors(
    primary = Stormy50,
    primaryVariant = Stormy70,
    secondary = Stormy30,
    secondaryVariant = Stormy90
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