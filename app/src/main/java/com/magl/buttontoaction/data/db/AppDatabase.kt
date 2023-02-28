package com.magl.buttontoaction.data.db

import com.magl.buttontoaction.data.db.converters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.magl.buttontoaction.data.db.dao.ActionDao
import com.magl.buttontoaction.data.model.db.ActionEntity

@Database(
    entities = [
        ActionEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun actionDao(): ActionDao
}
