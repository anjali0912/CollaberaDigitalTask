package com.example.collaberadigitaltask

import android.app.Activity
import android.content.Intent
import android.text.format.DateFormat
import com.example.collaberadigitaltask.home.HomeActivity
import com.example.collaberadigitaltask.login.LoginActivity
import com.example.collaberadigitaltask.register.RegisterActivity
import java.util.Calendar
import java.util.Locale

const val DATE_TIME_FORMAT = "HH:mm a"
const val ONE_THOUSAND = 1000
const val DECIMAL_ONE = 0.001
const val NORTHERLY = 337.5F
const val NORTH_WESTERLY = 292.5
const val WESTERLY = 247.5
const val SOUTH_WESTERLY = 202.5
const val SOUTHERLY = 157.5
const val SOUTH_EASTERLY = 122.5
const val EASTERLY = 67.5
const val NORTH_EASTERLY = 22.5

const val NORTHERLY_VALUE = "Northerly"
const val NORTH_WESTERLY_VALUE = "North Westerly"
const val WESTERLY_VALUE = "Westerly"
const val SOUTH_WESTERLY_VALUE = "South Westerly"
const val SOUTHERLY_VALUE = "Southerly"
const val SOUTH_EASTERLY_VALUE = "South Easterly"
const val EASTERLY_VALUE = "Easterly"
const val NORTH_EASTERLY_VALUE = "North Easterly"

fun getDate(time: Long): String {
    val cal = Calendar.getInstance(Locale.ENGLISH)
    cal.timeInMillis = time * ONE_THOUSAND
    return DateFormat.format(DATE_TIME_FORMAT, cal).toString()
}

fun degreeToText(degree: Double): String {
    return when {
        degree > NORTHERLY -> NORTHERLY_VALUE
        degree > NORTH_WESTERLY -> NORTH_WESTERLY_VALUE
        degree > WESTERLY -> WESTERLY_VALUE
        degree > SOUTH_WESTERLY -> SOUTH_WESTERLY_VALUE
        degree > SOUTHERLY -> SOUTHERLY_VALUE
        degree > SOUTH_EASTERLY -> SOUTH_EASTERLY_VALUE
        degree > EASTERLY -> EASTERLY_VALUE
        degree > NORTH_EASTERLY -> NORTH_EASTERLY_VALUE
        else -> NORTHERLY_VALUE
    }
}

fun doubleToKilometer(meter: Double): Int {
    return (meter * DECIMAL_ONE).toInt()
}

fun convertKelvinToCelcius(kelvinValue: Float): String {
    val kelvinToDegree = ((kelvinValue - 273.15) * 9 / 5) + 32
    return String.format("%.0f", kelvinToDegree)
}

fun navigateToLoginActivity(context: Activity) {
    context.startActivity(Intent(context, LoginActivity::class.java))
    context.finish()
}

fun navigateToRegisterActivity(context: Activity) {
    context.startActivity(Intent(context, RegisterActivity::class.java))
    context.finish()
}

fun navigateToHomeActivity(context: Activity) {
    context.startActivity(Intent(context, HomeActivity::class.java))
    context.finish()
}