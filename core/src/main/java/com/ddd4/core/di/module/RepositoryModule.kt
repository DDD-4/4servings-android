package com.ddd4.core.di.module

import com.ddd4.model.repository.DropitRepository
import com.ddd4.core.di.repositoryImpl.DropitRepositoryImpl
import com.ddd4.core.room.AppDataBase
import com.ddd4.core.room.DropitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun DropItRepository(dropitDao: DropitDao): DropitRepository {
        return DropitRepositoryImpl(dropitDao)
    }
}
