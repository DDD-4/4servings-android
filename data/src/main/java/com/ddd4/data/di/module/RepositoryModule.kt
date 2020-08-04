package com.ddd4.data.di.module

import com.ddd4.data.mapper.DropitDataMapper
import com.ddd4.data.mapper.DropitListMapper
import com.ddd4.domain.repository.DropitRepository
import com.ddd4.data.repository.DropitRepositoryImpl
import com.ddd4.data.source.DropitLocalDataSource
import com.ddd4.data.source.local.DropitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun DropItRepository(
        dropitLocalDataSource: DropitLocalDataSource,
        dropitListMapper: DropitListMapper,
        dropitDataMapper: DropitDataMapper,
        ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ): DropitRepository {
        return DropitRepositoryImpl(dropitLocalDataSource, dropitListMapper, dropitDataMapper, ioDispatcher)
    }
}
