package com.example.collaberadigitaltask.login

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collaberadigitaltask.R
import com.example.collaberadigitaltask.theme.colorTextDark
import com.example.collaberadigitaltask.theme.colorWhite
import com.example.domain.params.register.RegisterParams
import com.example.collaberadigitaltask.navigateToRegisterActivity

const val EMAIL_TEXT = "Email"
const val ENTER_TEXT = "Enter"
const val PASSWORD_TEXT = "Password"
const val LOGIN_TEXT = "LOGIN"
const val REGISTER_TEXT = "REGISTER"

@Composable
fun LoginView(viewModel: LoginViewModel, context: LoginActivity) {
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val emailCallBack: (String) -> Unit = {
        email.value = it
    }
    val passwordCallBack: (String) -> Unit = {
        password.value = it
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .wrapContentHeight()
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DisplayText(LOGIN_TEXT)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .wrapContentHeight()
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DisplayEmailField(emailCallBack)
            DisplayPasswordField(passwordCallBack)
            DisplayLoginButton(viewModel, context, email, password)
        }
    }
}

@Composable
fun DisplayText(text: String) {
    Image(
        modifier = Modifier.size(100.dp),
        painter = painterResource(id = R.drawable.cloud),
        contentDescription = "",
        contentScale = ContentScale.FillHeight
    )
    Text(
        modifier = Modifier,
        text = text,
        fontFamily = FontFamily.Serif,
        fontSize = 25.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun DisplayEmailField(email: (String) -> Unit) {
    val text = remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorTextDark.copy(alpha = 0.3f), shape = RoundedCornerShape(8.dp)
            ),
        value = text.value,
        onValueChange = {
            text.value = it
            email.invoke(text.value)
        },
        label = {
            Text(
                text = EMAIL_TEXT,
                color = colorWhite,
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp
            )
        },
        placeholder = {
            Text(
                text = "$ENTER_TEXT $EMAIL_TEXT",
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
        }),
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun DisplayPasswordField(passwordText: (String) -> Unit) {
    val text = remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    var password = remember { mutableStateOf("") }
    var passwordVisible = remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorTextDark.copy(alpha = 0.3f), shape = RoundedCornerShape(8.dp)
            ),
        value = password.value,
        onValueChange = {
            password.value = it
            passwordText.invoke(it)
        },
        label = {
            Text(
                text = PASSWORD_TEXT,
                color = colorWhite,
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp
            )
        },
        placeholder = {
            Text(
                text = "$ENTER_TEXT $PASSWORD_TEXT",
                color = colorWhite,
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp
            )
        },
        colors = TextFieldDefaults.textFieldColors(textColor = colorWhite),
        textStyle = TextStyle(
            fontFamily = FontFamily.Serif
        ),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        singleLine = true,
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible.value)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            // Please provide localized description for accessibility services
            val description = if (passwordVisible.value) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = image, description)
            }
        }
    )
}

@Composable
private fun DisplayLoginButton(
    viewModel: LoginViewModel,
    context: LoginActivity,
    email: MutableState<String>,
    password: MutableState<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .wrapContentHeight()
            .padding(all = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.width(200.dp),
            onClick = {
                loginData(viewModel, context, email.value, password.value)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = LOGIN_TEXT,
                color = Color.White
            )
        }

        Button(
            modifier = Modifier
                .width(200.dp)
                .padding(top = 8.dp),
            onClick = {
                navigateToRegisterActivity(context)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = REGISTER_TEXT,
                color = Color.White
            )
        }
    }
}

fun loginData(
    viewModel: LoginViewModel,
    context: Activity,
    email: String,
    password: String
) {
    if (email.isNotEmpty()
        && password.isNotEmpty()
    ) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            viewModel.login(RegisterParams(email = email, password = password))
        } else {
            displayToast(context, "Please enter valid email")
        }
    } else {
        displayToast(context, "Please enter all the details")
    }
}

private fun displayToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}
