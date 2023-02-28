package com.magl.buttontoaction.data.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ActionDto(
    @Json(name = "type")
    val type: String,
    @Json(name = "enabled")
    val enabled: Boolean,
    @Json(name = "priority")
    val priority: Int,
    @Json(name = "valid_days")
    val validDays: List<Int>,
    @Json(name = "cool_down")
    val coolDown: Long,
)
