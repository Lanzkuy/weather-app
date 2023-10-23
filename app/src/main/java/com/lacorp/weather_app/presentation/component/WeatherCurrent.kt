package com.lacorp.weather_app.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lacorp.weather_app.domain.model.WeatherCurrent
import com.lacorp.weather_app.presentation.theme.CulturedWhite

@Composable
fun WeatherCurrent(
    modifier: Modifier,
    weatherCurrent: WeatherCurrent,
    location: String = "",
    datetime: String = ""
) {
    weatherCurrent.let { data ->
        TextIcon(
            modifier = Modifier,
            imageVector = Icons.Default.LocationOn,
            text = location,
            textStyle = MaterialTheme.typography.h5,
            color = CulturedWhite,
            width = 30.dp,
            height = 30.dp,
            space = 2.dp,
            isFill = true
        )
        Spacer(modifier = Modifier.padding(top = 48.dp))
        TextIcon(
            modifier = Modifier,
            painterResourceId = data.weatherType.icon,
            text = data.weatherType.type,
            textStyle = MaterialTheme.typography.h5,
            color = CulturedWhite,
            width = 60.dp,
            height = 40.dp,
            space = 2.dp,
            isFill = true
        )
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${data.temperature.toInt()}°",
                style = MaterialTheme.typography.h2,
                color = CulturedWhite
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TextIcon(
                    modifier = Modifier,
                    imageVector = Icons.Default.KeyboardArrowUp,
                    text = "${data.maxTemperatures.toInt()}°",
                    textStyle = MaterialTheme.typography.body1,
                    color = CulturedWhite,
                    width = 24.dp,
                    height = 24.dp,
                    space = 2.dp,
                    isFill = false
                )
                TextIcon(
                    modifier = Modifier,
                    imageVector = Icons.Default.KeyboardArrowDown,
                    text = "${data.minTemperatures.toInt()}°",
                    textStyle = MaterialTheme.typography.body1,
                    color = CulturedWhite,
                    width = 24.dp,
                    height = 24.dp,
                    space = 2.dp,
                    isFill = false
                )
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = datetime,
            style = MaterialTheme.typography.body1,
            color = CulturedWhite,
            textAlign = TextAlign.Center
        )
    }
}