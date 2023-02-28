package com.magl.buttontoaction.data.api

import com.magl.buttontoaction.data.model.dto.ActionDto
import retrofit2.http.GET

const val DEFAULT_BASE_URL = "https://s3-us-west-2.amazonaws.com"

/**
 * REST API access point
 */
interface ApiClient {

    @GET("androidexam/butto_to_action_config.json")
    suspend fun fetchActions(): List<ActionDto>
}
