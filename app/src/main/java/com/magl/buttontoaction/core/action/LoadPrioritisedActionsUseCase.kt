package com.magl.buttontoaction.core.action

import com.magl.buttontoaction.core.model.Action
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

class LoadPrioritisedActionsUseCase @Inject constructor(
    private val actionRepository: ActionRepository,
) {
    suspend fun execute(): List<Action> =
        withContext(Dispatchers.Default) {
            actionRepository.loadActions()
                .filter { it.enabled }
                .filter { it.validDays.contains(LocalDate.now().dayOfWeek) }
                .filter { it.lastTimeUsed == null || (System.currentTimeMillis() - it.lastTimeUsed > it.coolDownPeriod) }
                .sortedByDescending { it.priority }
        }
}
