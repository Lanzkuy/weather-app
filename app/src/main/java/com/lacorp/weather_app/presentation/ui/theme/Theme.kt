package com.lacorp.weather_app.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

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

private val CloudyColorPallete = lightColors(
    primary = Cloudy50,
    primaryVariant = Cloudy70,
    secondary = Cloudy30,
)

private val SunnyColorPallete = lightColors(
    primary = Sunny50,
    primaryVariant = Sunny70,
    secondary = Sunny30,
)

private val RainyColorPallete = lightColors(
    primary = Rainy50,
    primaryVariant = Rainy70,
    secondary = Rainy30,
)

private val StormyColorPallete = lightColors(
    primary = Stormy50,
    primaryVariant = Stormy70,
    secondary = Stormy30,
)

@Composable
fun WeatherAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}