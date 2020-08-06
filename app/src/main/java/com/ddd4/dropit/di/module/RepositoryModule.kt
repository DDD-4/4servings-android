package com.ddd4.dropit.di.module

import com.ddd4.data.mapper.DropitDataMapper
import com.ddd4.data.mapper.DropitDataListMapper
import com.ddd4.domain.repository.DropitRepository
import com.ddd4.data.repository.DropitRepositoryImpl
import com.ddd4.data.source.DropitLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun DropItRepository(
        dropitLocalDataSource: DropitLocalDataSource,
        dropitDataListMapper: DropitDataListMapper,
        dropitDataMapper: DropitDataMapper,
        ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ): DropitRepository {
        return DropitRepositoryImpl(dropitLocalDataSource, dropitDataListMapper, dropitDataMapper, ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

}
