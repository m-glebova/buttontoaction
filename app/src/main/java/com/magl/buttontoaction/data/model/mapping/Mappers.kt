package com.magl.buttontoaction.data.model.mapping

import com.magl.buttontoaction.core.model.Action
import com.magl.buttontoaction.core.model.ActionType
import com.magl.buttontoaction.data.model.db.ActionEntity
import com.magl.buttontoaction.data.model.dto.ActionDto
import java.time.DayOfWeek

object Mappers {

    fun ActionDto.toActionEntity(): ActionEntity =
        ActionEntity(
            toEntityType(type),
            enabled,
            priority,
            validDays,
            coolDown,
            null
        )

    fun ActionEntity.toAction(): Action =
        Action(
            actionType,
            enabled,
            priority,
            validDays.map { it.toDayOfWeek() },
            coolDown,
            lastTimeUsedMillis
        )

    // From the Android Documentation:
    // The int value follows the ISO-8601 standard,
    // from 1 (Monday) to 7 (Sunday).
    private fun Int.toDayOfWeek(): DayOfWeek =
        when (this) {
            0 -> DayOfWeek.MONDAY
            1 -> DayOfWeek.TUESDAY
            2 -> DayOfWeek.WEDNESDAY
            3 -> DayOfWeek.THURSDAY
            4 -> DayOfWeek.FRIDAY
            5 -> DayOfWeek.SATURDAY
            6 -> DayOfWeek.SUNDAY
            else -> DayOfWeek.MONDAY
        }

    private fun toEntityType(value: String): ActionType =
        enumValues<ActionType>().find { actionType ->
            actionType.name.equals(value, ignoreCase = true)
        } ?: ActionType.TOAST
}
