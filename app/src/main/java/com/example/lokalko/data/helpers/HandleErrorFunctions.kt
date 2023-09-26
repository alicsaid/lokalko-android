package com.example.lokalko.data.helpers

import androidx.compose.runtime.MutableState
import com.example.lokalko.R
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun handleHttpException(errorMessage: MutableState<String>, code: Int) {
    errorMessage.value = when (code) {
        in 400..410 -> handleClientErrors(code)
        in 500..505 -> handleServerErrors(code)
        else -> "$code - Something went wrong"
    }
}

fun handleClientErrors(code: Int): String {
    return when (code) {
        400 -> "Error 400 (Bad Request)"
        401 -> "Incorrect email or password!" //Error 401 (Unauthorized)
        402 -> "Error 402 (Payment Required)"
        403 -> "You don't have access!" //Error 403 (Forbidden)
        404 -> "Resource not found!" //Error 404 (Not Found)
        405 -> "Error 405 (Method Not Allowed)"
        406 -> "Error 406 (Not Acceptable)"
        407 -> "Error 407 (Proxy Authentication Required)"
        408 -> "Error 408 (Request Timeout)"
        409 -> "Email address already exists. Try another one!" //Error 409 (Conflict)
        410 -> "Error 410 (Gone)"
        else -> "Something went wrong - $code"
    }
}

fun handleServerErrors(code: Int): String {
    return when (code) {
        500 -> "Error 500 (Internal Server Error)"
        501 -> "Error 501 (Not Implemented)"
        502 -> "Error 502 (Bad Gateway)"
        503 -> "Error 503 (Service Unavailable)"
        504 -> "Error 504 (Gateway Timeout)"
        505 -> "Error 505 (HTTP Version Not Supported)"
        else -> "Something went wrong - $code"
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

fun handleApiException(ex: Exception): Int {
    return when (ex) {
        is HttpException -> handleHttpException(ex)
        is IOException -> handleIoException(ex)
        else -> {
            println("API EXCEPTION ELSE")
            R.string.api_generic_error
        }
    }
}

fun handleHttpException(ex: HttpException): Int {
    println("HandleHTTPException $ex")
    return when (ex.code()) {
        in 400 until 500 -> R.string.api_error_4xx
        in 500 until 600 -> R.string.api_error_5xx
        else -> R.string.api_generic_error
    }
}

fun handleIoException(ex: IOException): Int {
    println("HandleIOException $ex")
    return when (ex) {
        is SocketTimeoutException -> R.string.api_generic_error
        is UnknownHostException -> R.string.no_internet1
        else -> R.string.api_generic_error
    }
}

fun handleRegisterApiException(ex: Exception): Int {
    return when (ex) {
        is HttpException -> handleHttpExceptionForRegisterApi(ex)
        is IOException -> handleIoException(ex)
        else -> R.string.api_generic_error
    }
}

fun handleHttpExceptionForRegisterApi(ex: HttpException): Int {
    println(ex.code())
    return when (ex.code()) {
        in 400 until 500 -> R.string.user_already_exists
        in 500 until 600 -> R.string.api_error_5xx
        else -> R.string.api_generic_error
    }
}