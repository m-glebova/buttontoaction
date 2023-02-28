package com.magl.buttontoaction.util

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val ex: Exception) : Result<Nothing>()
    object NetworkError : Result<Nothing>()
    object Empty : Result<Nothing>()
}

val Result<*>.succeeded
    get() = this is Result.Success && data != null

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data

inline fun <T, R> Result<T>.map(block: (T) -> R): Result<R> =
    when (this) {
        is Result.Success -> Result.Success(block(this.data))
        is Result.Error -> this
        is Result.NetworkError -> this
        is Result.Empty -> this
    }

fun Result<*>.mapUnit(): Result<Unit> = map { /* no op */ }
