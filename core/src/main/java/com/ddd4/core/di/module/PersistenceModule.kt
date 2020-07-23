package com.ddd4.core.di.module

import android.app.Application
import com.ddd4.core.di.qualifier.ForApplication
import com.ddd4.core.helper.SharedPreferenceRepository
import com.ddd4.core.helper.SharedPreferenceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    //Room

    //And SharedPref !
    @Provides
    @Singleton
    @ForApplication
    internal fun provideSharedPrefs(application: Application): SharedPreferenceRepository {
        return SharedPreferenceRepositoryImpl(application)
    }
}