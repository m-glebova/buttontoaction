package com.magl.buttontoaction.core.action

import com.magl.buttontoaction.core.model.Action
import com.magl.buttontoaction.core.model.ActionType
import com.magl.buttontoaction.util.Result
import kotlinx.coroutines.flow.Flow

interface ActionRepository {
    suspend fun loadActions(): List<Action>

    suspend fun fetchActions(): Result<Unit>

    suspend fun saveLastTimeActionUsed(actionType: ActionType, millis: Long): Result<Unit>

    fun hasActions(): Flow<Boolean>
}
