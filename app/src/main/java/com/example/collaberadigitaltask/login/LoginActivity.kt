package com.example.collaberadigitaltask.login

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.collaberadigitaltask.R
import com.example.collaberadigitaltask.navigateToHomeActivity
import com.example.core.BaseActivity
import com.example.core.BaseEvent

class LoginActivity : BaseActivity<LoginEvents, LoginData, LoginViewModel>(
    LoginViewModel::class,
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
                LoginView(viewModel, this@LoginActivity)
            }
        }
    }

    override fun eventUpdated(event: BaseEvent) {
        when (event) {
            is LoginEvents.OnSuccess -> {
                navigateToHomeActivity(this@LoginActivity)
            }
            is LoginEvents.OnFailure -> {
                displayToast(this@LoginActivity, "User does not exist")
            }
        }
    }
}

private fun displayToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}