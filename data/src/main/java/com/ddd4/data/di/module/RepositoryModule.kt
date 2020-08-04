package com.ddd4.data.di.module

import com.ddd4.domain.repository.DropitRepository
import com.ddd4.data.di.repositoryImpl.DropitRepositoryImpl
import com.ddd4.data.room.DropitDao
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
