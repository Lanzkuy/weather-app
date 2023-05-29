package com.lacorp.weather_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lacorp.weather_app.R
import com.lacorp.weather_app.presentation.ui.theme.CulturedWhite
import com.lacorp.weather_app.presentation.ui.theme.WeatherAppTheme
import com.lacorp.weather_app.util.WeatherEnum
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp()
        }
    }
}

@Composable
fun WeatherApp() {
    WeatherAppTheme(WeatherEnum.CLOUDY) {
        val colorPrimary = MaterialTheme.colors.primary
        val colorPrimaryVariant = MaterialTheme.colors.primaryVariant
        val colorSecondary = MaterialTheme.colors.secondary
        val colorSecondaryVariant = MaterialTheme.colors.primaryVariant

        val gradientBackground = listOf(colorPrimary, colorSecondary)
        val gradientBrush = Brush.verticalGradient(gradientBackground)

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = gradientBrush)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Bottom
                ) {
                    Icon(
                        modifier = Modifier
                            .size(36.dp)
                            .align(CenterVertically),
                        imageVector = Icons.Default.LocationOn,
                        tint = CulturedWhite,
                        contentDescription = null,
                    )
                    Text(
                        text = "JAKARTA",
                        color = CulturedWhite,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Light
                    )
                }
                Spacer(modifier = Modifier.padding(top = 32.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Bottom
                ) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp, 48.dp),
                        painter = painterResource(id = R.drawable.weather_cloudy),
                        tint = CulturedWhite,
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "Cloudy",
                        color = CulturedWhite,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Light
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherApp()
}