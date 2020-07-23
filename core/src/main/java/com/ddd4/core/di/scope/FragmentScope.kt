package com.ddd4.core.di.scope

import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Scope

@Scope
@InstallIn(FragmentComponent::class)
annotation class FragmentScope