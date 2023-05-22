package com.example.collaberadigitaltask.home.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.collaberadigitaltask.R
import com.example.domain.model.weather.WeatherModel
import com.example.collaberadigitaltask.convertKelvinToCelcius
import com.example.collaberadigitaltask.degreeToText
import com.example.collaberadigitaltask.doubleToKilometer
import com.example.collaberadigitaltask.getDate
import com.example.collaberadigitaltask.home.HomeViewModel
import com.example.collaberadigitaltask.theme.colorPrimary
import com.example.collaberadigitaltask.theme.colorTextDark
import com.example.collaberadigitaltask.theme.colorWhite
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

const val SURFACE_ALPHA = 0.9F
const val ERROR_MSG = "Something went wrong...Please try again"
const val SEARCH_TEXT = "Search"
const val SEARCH_HINT_TEXT = "Enter city name"

@Composable
fun MainView(viewModel: HomeViewModel, cityName: (String) -> Unit) {
    when {
        viewModel.state.value.loadingState -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorPrimary.copy(alpha = SURFACE_ALPHA)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp), color = colorWhite, strokeWidth = 5.dp
                )
            }
        }
        viewModel.state.value.errorState -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = ERROR_MSG,
                    color = colorWhite,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center
                )
                Icon(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable {
                            cityName.invoke("")
                        },
                    painter = painterResource(id = R.drawable.ic_refresh),
                    tint = colorWhite,
                    contentDescription = "",
                )
            }
        }
        else -> {
            WeatherView(viewModel.state.value.weatherModel, cityName)
        }
    }
}

@Composable
fun WeatherView(weatherModel: WeatherModel, cityName: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SearchView(cityName)

        NameAndTimeZone(weatherModel)

        WeatherTemperatureCard(
            minTemp = convertKelvinToCelcius(weatherModel.tempMin.toFloat()),
            maxTemp = convertKelvinToCelcius(weatherModel.tempMax.toFloat()),
            sunset = getDate(weatherModel.sunset.toLong()),
            sunrise = getDate(weatherModel.sunrise.toLong())
        )

        DisplayMapView(
            weatherModel.latitude.toDouble(),
            weatherModel.longitude.toDouble(),
            convertKelvinToCelcius(weatherModel.feelsLike.toFloat()),
            weatherModel.name
        )

        WeatherCard(getFeelsLikeHumidity(weatherModel))
        WeatherCard(getVisibilityPressure(weatherModel))
        WeatherCard(getWindPrecipitation(weatherModel))
    }
}

@Composable
fun NameAndTimeZone(weatherModel: WeatherModel) {
    val name = weatherModel.name
    val country = weatherModel.country

    val weatherData = if (weatherModel.weather.isNotEmpty()) {
        val main = weatherModel.weather.first().main
        val description = weatherModel.weather.first().description
        val icon = weatherModel.weather.first().icon
        Triple(main, description, icon)
    } else {
        Triple("", "", "")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(60.dp),
            painter = rememberImagePainter(
                data = "https://openweathermap.org/img/wn/${weatherData.third}@2x.png"
            ),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = "$name, $country",
            modifier = Modifier.fillMaxWidth(),
            color = colorWhite,
            fontSize = 24.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif
        )
        Text(
            text = "${weatherData.first} | ${weatherData.second}",
            modifier = Modifier.fillMaxWidth(),
            color = colorWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif
        )
    }
}

fun getVisibilityPressure(weatherModel: WeatherModel): Pair<WeatherCardData, WeatherCardData> {
    val visibility = doubleToKilometer(weatherModel.visibility.toDouble())
    val pressure = weatherModel.pressure
    return Pair(
        WeatherCardData(
            icon = R.drawable.ic_visibility,
            title = "VISIBILITY",
            value = "$visibility km",
            subtitle = "Light haze is affecting visibility"
        ), WeatherCardData(
            icon = R.drawable.ic_pressure,
            title = "PRESSURE",
            value = "$pressure hpa",
            subtitle = "Its perfectly clear right now"
        )
    )
}

fun getWindPrecipitation(weatherModel: WeatherModel): Pair<WeatherCardData, WeatherCardData> {
    val windSpeed = weatherModel.speed
    val windDirection = degreeToText(weatherModel.deg.toDouble())
    val gustLevel = weatherModel.gust.toString()
    return Pair(
        WeatherCardData(
            icon = R.drawable.ic_wind,
            title = "WIND",
            value = "$windSpeed kph",
            subtitle = "$windDirection for the day"
        ), WeatherCardData(
            icon = R.drawable.ic_precipitation,
            title = "GUST LEVEL",
            value = gustLevel,
            subtitle = "None expected in next 10 days"
        )
    )
}

fun getFeelsLikeHumidity(weatherModel: WeatherModel): Pair<WeatherCardData, WeatherCardData> {
    val feelsLike = convertKelvinToCelcius(weatherModel.feelsLike.toFloat())
    val humidity = weatherModel.humidity
    return Pair(
        WeatherCardData(
            icon = R.drawable.ic_feels_like,
            title = "FEELS LIKE",
            value = "${feelsLike}°",
            subtitle = "Similar to the actual temperature"
        ), WeatherCardData(
            icon = R.drawable.ic_humidity,
            title = "HUMIDITY",
            value = "${humidity}%",
            subtitle = "The dew point is 0° right now"
        )
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchView(cityName: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .wrapContentHeight()
            .padding(all = 8.dp)
    ) {
        val text = remember {
            mutableStateOf("")
        }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorTextDark.copy(alpha = 0.3f), shape = RoundedCornerShape(8.dp)
                ),
            value = text.value,
            onValueChange = {
                text.value = it
            },
            label = {
                Text(
                    text = SEARCH_TEXT,
                    color = colorWhite,
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp
                )
            },
            placeholder = {
                Text(
                    text = SEARCH_HINT_TEXT,
                    color = colorWhite,
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp
                )
            },
            colors = TextFieldDefaults.textFieldColors(textColor = colorWhite),
            textStyle = TextStyle(
                fontFamily = FontFamily.Serif
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                cityName.invoke(text.value)
            }),
        )
    }
}

@Composable
fun WeatherContents(label: String, value: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 5.dp),
        text = label,
        fontSize = 20.sp,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = FontFamily.Serif
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 5.dp),
        text = value,
        color = Color.Black,
        fontSize = 16.sp,
        fontWeight = FontWeight.Light,
        fontFamily = FontFamily.Serif,
    )
}

@Composable
fun WeatherCard(weatherCardData: Pair<WeatherCardData, WeatherCardData>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(all = 5.dp)
                .weight(1f)
                .background(
                    color = colorTextDark.copy(alpha = 0.8f), shape = RoundedCornerShape(8.dp)
                )

        ) {
            val modelStart = weatherCardData.first
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        colorWhite.copy(alpha = 0.1f), shape = RoundedCornerShape(
                            topStart = 8.dp, topEnd = 8.dp, bottomStart = 0.dp, bottomEnd = 0.dp
                        )
                    )
            ) {
                Icon(
                    modifier = Modifier.padding(all = 5.dp),
                    painter = painterResource(id = modelStart.icon),
                    contentDescription = "",
                    tint = colorWhite.copy(alpha = 0.9f)
                )
                Text(
                    modifier = Modifier.padding(all = 5.dp),
                    text = modelStart.title,
                    color = colorWhite.copy(alpha = 0.9f),
                    fontFamily = FontFamily.Serif,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = modelStart.value,
                    modifier = Modifier.padding(start = 20.dp, top = 8.dp),
                    fontSize = 25.sp,
                    color = colorWhite,
                    fontFamily = FontFamily.Serif,
                )
                Text(
                    text = modelStart.subtitle,
                    modifier = Modifier
                        .padding(
                            start = 20.dp,
                            bottom = 8.dp,
                            end = 16.dp
                        ),
                    fontSize = 18.sp,
                    color = colorWhite.copy(alpha = 0.8f),
                    fontFamily = FontFamily.Serif,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(all = 5.dp)
                .weight(1f)
                .background(
                    color = colorTextDark.copy(alpha = 0.8f), shape = RoundedCornerShape(8.dp)
                )

        ) {
            val modelEnd = weatherCardData.second
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        colorWhite.copy(alpha = 0.1f), shape = RoundedCornerShape(
                            topStart = 8.dp, topEnd = 8.dp, bottomStart = 0.dp, bottomEnd = 0.dp
                        )
                    )
            ) {

                Icon(
                    modifier = Modifier.padding(all = 5.dp),
                    painter = painterResource(id = modelEnd.icon),
                    contentDescription = "",
                    tint = colorWhite.copy(alpha = 0.9f)
                )
                Text(
                    modifier = Modifier.padding(all = 5.dp),
                    text = modelEnd.title,
                    color = colorWhite.copy(alpha = 0.9f),
                    fontFamily = FontFamily.Serif
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = modelEnd.value,
                    modifier = Modifier.padding(start = 20.dp, top = 8.dp),
                    fontSize = 25.sp,
                    color = colorWhite,
                    fontFamily = FontFamily.Serif,
                )
                Text(
                    text = modelEnd.subtitle,
                    modifier = Modifier
                        .padding(
                            start = 20.dp,
                            bottom = 8.dp,
                            end = 16.dp
                        ),
                    fontSize = 18.sp,
                    color = colorWhite.copy(alpha = 0.8f),
                    fontFamily = FontFamily.Serif,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun WeatherTemperatureCard(
    minTemp: String,
    maxTemp: String,
    sunset: String,
    sunrise: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(all = 5.dp)
                .weight(1f)
                .background(
                    color = colorTextDark.copy(alpha = 0.8f), shape = RoundedCornerShape(8.dp)
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        colorWhite.copy(alpha = 0.1f), shape = RoundedCornerShape(
                            topStart = 8.dp, topEnd = 8.dp, bottomStart = 0.dp, bottomEnd = 0.dp
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    modifier = Modifier.padding(all = 5.dp),
                    painter = painterResource(id = R.drawable.ic_brightness),
                    contentDescription = "",
                    tint = colorWhite.copy(alpha = 0.9f)
                )
                Text(
                    modifier = Modifier.padding(all = 5.dp),
                    text = "TEMPERATURE",
                    color = colorWhite.copy(alpha = 0.9f),
                    fontFamily = FontFamily.Serif
                )
            }

            Text(
                text = "Min: ${minTemp}°",
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        end = 16.dp
                    ),
                fontSize = 16.sp,
                color = colorWhite.copy(alpha = 0.8f),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Max: ${maxTemp}°",
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        bottom = 16.dp,
                        end = 16.dp
                    ),
                fontSize = 16.sp,
                color = colorWhite.copy(alpha = 0.8f),
                fontFamily = FontFamily.Serif
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(all = 5.dp)
                .weight(1f)
                .background(
                    color = colorTextDark.copy(alpha = 0.8f), shape = RoundedCornerShape(8.dp)
                ), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        colorWhite.copy(alpha = 0.1f), shape = RoundedCornerShape(
                            topStart = 8.dp, topEnd = 8.dp, bottomStart = 0.dp, bottomEnd = 0.dp
                        )
                    )
            ) {

                Icon(
                    modifier = Modifier.padding(all = 5.dp),
                    painter = painterResource(id = R.drawable.ic_sunset),
                    contentDescription = "",
                    tint = colorWhite.copy(alpha = 0.9f)
                )
                Text(
                    modifier = Modifier.padding(all = 5.dp),
                    text = "SUNSET",
                    color = colorWhite.copy(alpha = 0.9f),
                    fontFamily = FontFamily.Serif
                )
            }

            Text(
                text = "${sunset}",
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        end = 16.dp
                    ),
                fontSize = 16.sp,
                color = colorWhite.copy(alpha = 0.8f),
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "Sunrise: ${sunrise}",
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        bottom = 16.dp,
                        end = 16.dp
                    ),
                fontSize = 16.sp,
                color = colorWhite.copy(alpha = 0.8f),
                fontFamily = FontFamily.Serif
            )
        }
    }
}

@Composable
private fun DisplayMapView(latitude: Double, longitude: Double, feelsLike: String, name: String) {
    val currentLocation = LatLng(latitude, longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 10f)
    }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(200.dp)
            .background(
                color = colorTextDark.copy(alpha = 0.8f),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        GoogleMap(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                position = currentLocation,
                title = "Temperature",
                snippet = "$feelsLike in $name",
            )
        }
    }
}