package com.example.collaberadigitaltask.register

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.collaberadigitaltask.R
import com.example.core.BaseActivity
import com.example.core.BaseEvent

class RegisterActivity : BaseActivity<RegisterEvents, RegisterData, RegisterViewModel>(
    RegisterViewModel::class,
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.weather),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )
                RegisterView(viewModel, this@RegisterActivity)
            }
        }
    }

    override fun eventUpdated(event: BaseEvent) {
        when (event) {
            is RegisterEvents.OnSuccess -> {
            }
            is RegisterEvents.OnFailure -> {
            }
        }
    }
}