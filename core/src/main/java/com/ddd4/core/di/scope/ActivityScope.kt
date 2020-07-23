package com.ddd4.core.di.scope

import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Scope

@Scope
@InstallIn(ApplicationComponent::class)
annotation class ActivityScope