package com.ddd4.core.di.module

import com.ddd4.core.repository.DropitRepository
import com.ddd4.core.repository.DropitRepositoryImpl
import com.ddd4.core.room.AppDataBase
import com.ddd4.core.room.DropitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideBookListDao(appDatabase: AppDataBase): DropitDao {
        return appDatabase.dropItDao()
    }

    @Provides
    @ActivityRetainedScoped
    fun bookListRepository(dropitDao: DropitDao): DropitRepository {
        return DropitRepositoryImpl(dropitDao)
    }

}
