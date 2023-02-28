package com.magl.buttontoaction.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.magl.buttontoaction.core.model.ActionType

@Entity(tableName = "action")
data class ActionEntity(
    @PrimaryKey
    @ColumnInfo(name = "action_type")
    val actionType: ActionType,
    @ColumnInfo(name = "enabled")
    val enabled: Boolean,
    @ColumnInfo(name = "priority")
    val priority: Int,
    @ColumnInfo(name = "valid_days")
    val validDays: List<Int>,
    @ColumnInfo(name = "cool_down")
    val coolDown: Long,
    @ColumnInfo(name = "last_time_used_millis")
    val lastTimeUsedMillis: Long?,
)
