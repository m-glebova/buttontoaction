package com.magl.buttontoaction.core.action

import com.magl.buttontoaction.core.model.ActionType
import com.magl.buttontoaction.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveActionTimeUseCase @Inject constructor(
    private val actionRepository: ActionRepository,
) {

    suspend fun execute(actionType: ActionType, millis: Long): Result<Unit> =
        withContext(Dispatchers.IO) {
            actionRepository.saveLastTimeActionUsed(actionType, millis)
        }
}
