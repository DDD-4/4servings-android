package com.ddd4.dropit.di.module

import com.ddd4.dropit.domain.repository.DropitRepository
import com.ddd4.dropit.data.repository.DropitRepositoryImpl
import com.ddd4.dropit.data.source.local.LocalDataSource
import com.ddd4.dropit.data.source.local.LocalDataSourceImpl
import com.ddd4.dropit.data.source.local.preferences.SharedPrefHelper
import com.ddd4.dropit.data.source.local.room.DatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDropitRepository(
        localDataSource: LocalDataSource
    ): DropitRepository {
        return DropitRepositoryImpl(localDataSource)
    }

    //@Provides
    //@Singleton
    //fun provideIoDispatcher() = Dispatchers.IO

    @Provides
    @Singleton
    fun provideDatabaseRepository(
        databaseDao: DatabaseDao,
        sharedPrefHelper: SharedPrefHelper
    ): LocalDataSource {
        return LocalDataSourceImpl(databaseDao, sharedPrefHelper)
    }
}
