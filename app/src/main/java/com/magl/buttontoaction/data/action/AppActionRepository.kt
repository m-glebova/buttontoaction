package com.magl.buttontoaction.data.action

import com.magl.buttontoaction.core.action.ActionRepository
import com.magl.buttontoaction.core.model.Action
import com.magl.buttontoaction.core.model.ActionType
import com.magl.buttontoaction.data.model.mapping.Mappers.toAction
import com.magl.buttontoaction.data.model.mapping.Mappers.toActionEntity
import com.magl.buttontoaction.util.Result
import com.magl.buttontoaction.util.data
import com.magl.buttontoaction.util.mapUnit
import com.magl.buttontoaction.util.succeeded
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppActionRepository @Inject constructor(
    private val actionRemoteDataSource: ActionRemoteDataSource,
    private val actionLocalDataSource: ActionLocalDataSource,
) : ActionRepository {

    override suspend fun loadActions(): List<Action> =
        actionLocalDataSource.loadActions().map { it.toAction() }

    override suspend fun fetchActions(): Result<Unit> {
        val result = actionRemoteDataSource.fetchActions()
        if (result.succeeded) {
            result.data?.let { actions -> actionLocalDataSource.saveActions(actions.map { it.toActionEntity() }) }
        }
        return result.mapUnit()
    }

    override suspend fun saveLastTimeActionUsed(
        actionType: ActionType,
        millis: Long,
    ): Result<Unit> =
        runCatching {
            actionLocalDataSource.saveLastTimeActionUsed(actionType, millis)
            Result.Success(Unit)
        }.getOrElse { Result.Error(Exception(it)) }

    override fun hasActions(): Flow<Boolean> = actionLocalDataSource.hasActions()
}
