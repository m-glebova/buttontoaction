package com.magl.buttontoaction.data.db.converters

import androidx.room.TypeConverter
import com.magl.buttontoaction.core.model.ActionType

class Converters {

    @TypeConverter
    fun fromEntityType(value: ActionType?): String? = value?.name

    @TypeConverter
    fun toEntityType(value: String?): ActionType? = value?.let {
        enumValues<ActionType>().find { actionType ->
            actionType.name.equals(it, ignoreCase = true)
        }
    }

    @TypeConverter
    fun fromWeekDayList(value: List<Int>?): String? = value?.joinToString(SEPARATOR)

    @TypeConverter
    fun toWeekDayList(value: String?): List<Int>? = value?.split(",")?.map { it.toInt() }

    companion object {
        const val SEPARATOR = ","
    }
}
