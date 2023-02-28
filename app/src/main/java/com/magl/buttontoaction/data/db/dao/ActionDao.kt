package com.magl.buttontoaction.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.magl.buttontoaction.core.model.ActionType
import com.magl.buttontoaction.data.model.db.ActionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActionDao {

    @Transaction
    suspend fun saveActions(actions: List<ActionEntity>) {
        actions.forEach {
            val lastTimeUsed = loadLastTimeUsed(it.actionType)
            saveAction(it.copy(lastTimeUsedMillis = lastTimeUsed))
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAction(action: ActionEntity)

    @Query("SELECT * FROM action")
    suspend fun loadActions(): List<ActionEntity>

    @Query("SELECT last_time_used_millis FROM action WHERE action_type = :type")
    suspend fun loadLastTimeUsed(type: ActionType): Long?

    @Query(
        """
        UPDATE action 
        SET last_time_used_millis = :millis   
        WHERE action_type = :type
        """
    )
    suspend fun updateAction(type: ActionType, millis: Long)

    @Query("SELECT COUNT(*) > 0 FROM action")
    fun hasActions(): Flow<Boolean>
}
