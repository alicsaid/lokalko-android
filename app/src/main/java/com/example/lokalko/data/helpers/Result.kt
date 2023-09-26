package com.example.lokalko.data.helpers

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val errorMessageId: Int) : Result<Nothing>()
}