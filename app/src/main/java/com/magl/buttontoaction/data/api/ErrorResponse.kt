package com.magl.buttontoaction.data.api

import com.squareup.moshi.Json

data class ErrorResponse(
    @Json(name = "errors")
    val errors: List<Error?>?,
    @Json(name = "status")
    val status: String?
) {
    data class Error(
        @Json(name = "code")
        val code: String?,
        @Json(name = "message")
        val message: String?,
    )
}
