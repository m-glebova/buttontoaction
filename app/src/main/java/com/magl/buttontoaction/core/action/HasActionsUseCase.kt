package com.magl.buttontoaction.core.action

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HasActionsUseCase @Inject constructor(
    private val actionRepository: ActionRepository,
) {
    fun execute(): Flow<Boolean> = actionRepository.hasActions()
}
