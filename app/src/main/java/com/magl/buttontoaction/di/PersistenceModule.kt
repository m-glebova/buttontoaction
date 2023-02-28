package com.magl.buttontoaction.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.magl.buttontoaction.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context) =
        Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "action.db")
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()
}
