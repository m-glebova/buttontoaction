package com.magl.buttontoaction.data.action

import com.magl.buttontoaction.data.api.ApiClient
import com.magl.buttontoaction.data.api.RemoteDataSource
import com.magl.buttontoaction.data.model.dto.ActionDto
import com.magl.buttontoaction.util.Result
import javax.inject.Inject

class ActionRemoteDataSource @Inject constructor(
    private val webService: ApiClient,
) : RemoteDataSource() {

    suspend fun fetchActions(): Result<List<ActionDto>> =
        safeApiCall { webService.fetchActions() }
}
