package com.example.collaberadigitaltask.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.collaberadigitaltask.R
import com.example.core.BaseActivity
import com.example.core.BaseEvent
import com.example.domain.model.recent.RecentEntity

class HomeActivity : BaseActivity<HomeEvents, HomeData, HomeViewModel>(
    HomeViewModel::class,
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val recentLocationList = remember {
                mutableStateOf(emptyList<RecentEntity>())
            }
            viewModel.allRecentLocation.observe(this) {
                recentLocationList.value = it
            }

            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.weather),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )
                TabView(viewModel, recentLocationList.value)
            }
        }
    }

    override fun eventUpdated(event: BaseEvent) {
        when (event) {
            is HomeEvents.OnSuccess -> {
            }
            is HomeEvents.OnFailure -> {
            }
        }
    }
}