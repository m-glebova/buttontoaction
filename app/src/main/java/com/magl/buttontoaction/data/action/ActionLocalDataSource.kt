package com.magl.buttontoaction.data.action

import com.magl.buttontoaction.core.model.ActionType
import com.magl.buttontoaction.data.db.AppDatabase
import com.magl.buttontoaction.data.model.db.ActionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActionLocalDataSource @Inject constructor(
    private val db: AppDatabase,
) {
    private val actionDao = db.actionDao()

    suspend fun loadActions(): List<ActionEntity> =
        withContext(Dispatchers.IO) {
            actionDao.loadActions()
        }

    suspend fun saveActions(actions: List<ActionEntity>) {
        withContext(Dispatchers.IO) {
            actionDao.saveActions(actions)
        }
    }

    suspend fun saveLastTimeActionUsed(actionType: ActionType, millis: Long) {
        withContext(Dispatchers.IO) {
            actionDao.updateAction(actionType, millis)
        }
    }

    fun hasActions(): Flow<Boolean> = actionDao.hasActions()
}
