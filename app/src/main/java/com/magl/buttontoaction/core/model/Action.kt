package com.magl.buttontoaction.core.model

import java.time.DayOfWeek

data class Action(
    val type: ActionType,
    val enabled: Boolean,
    val priority: Int,
    val validDays: List<DayOfWeek>,
    val coolDownPeriod: Long,
    val lastTimeUsed: Long?,
)
