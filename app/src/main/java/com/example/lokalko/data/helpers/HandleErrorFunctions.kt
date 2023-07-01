package com.example.lokalko.data.helpers

import androidx.compose.runtime.MutableState

fun handleHttpException(errorMessage: MutableState<String>, code: Int) {
    errorMessage.value = when (code) {
        in 400..405 -> "Error 4XX"
        in 500..505 -> "Error 5XX"
        in 0..100 -> "Error 0"
        else -> "Something went wrong"
    }
}

fun handleServerUnavailableException(errorMessage: MutableState<String>) {
    errorMessage.value = "Server is currently unavailable. Please try again later."
}

fun handleServerDownException(errorMessage: MutableState<String>) {
    errorMessage.value = "Something went wrong. Please try again later."
}

fun handleNoInternetException(errorMessage: MutableState<String>) {
    errorMessage.value = "Please check your internet connection!"
}