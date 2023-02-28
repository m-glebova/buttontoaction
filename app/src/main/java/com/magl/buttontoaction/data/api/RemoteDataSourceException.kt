package com.magl.buttontoaction.data.api

class RemoteDataSourceException(
    message: String? = "Unknown API error",
    cause: Throwable? = null
) : Exception(message, cause)
