package com.ddd4.dropit.di.module

import android.app.Application
import androidx.room.Room
import com.ddd4.dropit.data.source.local.preferences.SharedPrefHelper
import com.ddd4.dropit.data.source.local.room.AppDataBase
import com.ddd4.dropit.data.source.local.room.DatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PersistenceModule {

    //Room
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDataBase {
        return Room.databaseBuilder(application, AppDataBase::class.java, "DropItDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    //Room Dao
    @Provides
    @Singleton
    fun provideDatabaseDao(appDatabase: AppDataBase): DatabaseDao {
        return appDatabase.databaseDao()
    }

    //SharedPreferences
    @Provides
    @Singleton
    fun provideSharedPrefHelper(application: Application): SharedPrefHelper {
        return SharedPrefHelper(application)
    }
}
