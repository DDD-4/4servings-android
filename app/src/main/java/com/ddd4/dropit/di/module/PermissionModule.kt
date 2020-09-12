package com.ddd4.dropit.di.module

import android.content.Context
import com.ddd4.dropit.presentation.util.permission.PermissionHelper
import com.ddd4.dropit.presentation.util.permission.PermissionHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PermissionModule {

    @Provides
    @Singleton
    fun providePermissionHelper(@ApplicationContext context: Context): PermissionHelper =
        PermissionHelperImpl(context)
}