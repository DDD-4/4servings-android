package com.ddd4.dropit.di.module

import android.app.Application
import androidx.room.Room
import com.ddd4.domain.repository.SharedPrefRepository
import com.ddd4.data.repository.SharedPrefRepositoryImpl
import com.ddd4.data.source.local.AppDataBase
import com.ddd4.data.source.local.DropitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    //Room
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDataBase {
        return Room.databaseBuilder(application, AppDataBase::class.java,
            "DropItDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDropItDao(appDatabase: AppDataBase): DropitDao {
        return appDatabase.dropItDao()
    }

    @Provides
    @Singleton
    fun provideSharedPrefs(application: Application): SharedPrefRepository {
        return SharedPrefRepositoryImpl(
            application
        )
    }
}
