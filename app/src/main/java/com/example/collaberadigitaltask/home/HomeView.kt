package com.example.collaberadigitaltask.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.collaberadigitaltask.R
import com.example.collaberadigitaltask.home.weather.MainView
import com.example.domain.model.recent.RecentEntity
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collaberadigitaltask.convertKelvinToCelcius
import com.example.collaberadigitaltask.getDate
import com.example.collaberadigitaltask.theme.colorTextDark
import com.example.collaberadigitaltask.theme.colorWhite

@Composable
fun TabView(viewModel: HomeViewModel, recentLocationList: List<RecentEntity>) {
    var tabIndex = remember { mutableStateOf(0) }

    val tabs = listOf("Search", "Recent")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            modifier = Modifier,
            backgroundColor = Color.Black,
            selectedTabIndex = tabIndex.value,
            contentColor = Color.White
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex.value == index,
                    onClick = { tabIndex.value = index }
                )
            }
        }
        when (tabIndex.value) {
            0 -> HomeScreen(viewModel)
            1 -> RecentScreen(recentLocationList)
        }
    }
}

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val cityName: (String) -> Unit = { cityName ->
        viewModel.getWeather(cityName)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.weather),
            contentDescription = "",
            contentScale = ContentScale.FillHeight
        )
        MainView(viewModel, cityName)
    }
}

@Composable
private fun RecentScreen(list: List<RecentEntity>) {

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(list) { item ->
                DisplayLocation(item)
            }
        }
    }
}

@Composable
private fun DisplayLocation(entity: RecentEntity) {
    val minTemp = convertKelvinToCelcius(entity.tempMin.toFloat())
    val maxTemp = convertKelvinToCelcius(entity.tempMax.toFloat())
    val sunset = getDate(entity.sunset.toLong())
    val sunrise = getDate(entity.sunrise.toLong())
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
                .wrapContentHeight()
                .padding(all = 5.dp)
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
                    text = "${entity.name}, ${entity.country}",
                    color = colorWhite.copy(alpha = 0.9f),
                    fontFamily = FontFamily.Serif
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp)
            ) {

                Text(
                    text = "Min: ${minTemp}°",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 16.dp
                        )
                        .weight(1f),
                    fontSize = 16.sp,
                    color = colorWhite.copy(alpha = 0.8f),
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = "Max: ${maxTemp}°",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 16.dp
                        )
                        .weight(1f),
                    fontSize = 16.sp,
                    color = colorWhite.copy(alpha = 0.8f),
                    fontFamily = FontFamily.Serif
                )
            }

            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(all = 8.dp)
            ) {

                Text(
                    text = "Sunrise: $sunrise",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 16.dp
                        )
                        .weight(1f),
                    fontSize = 16.sp,
                    color = colorWhite.copy(alpha = 0.8f),
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = "Sunrise: $sunset",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 16.dp
                        )
                        .weight(1f),
                    fontSize = 16.sp,
                    color = colorWhite.copy(alpha = 0.8f),
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}