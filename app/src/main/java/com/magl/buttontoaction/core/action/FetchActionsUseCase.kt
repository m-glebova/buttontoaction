package com.magl.buttontoaction.core.action

import com.magl.buttontoaction.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchActionsUseCase @Inject constructor(
    private val actionRepository: ActionRepository,
) {

    suspend fun execute(): Result<Unit> =
        withContext(Dispatchers.IO) {
            actionRepository.fetchActions()
        }
}
