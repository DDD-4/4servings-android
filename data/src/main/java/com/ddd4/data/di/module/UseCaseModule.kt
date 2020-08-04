package com.ddd4.data.di.module

import com.ddd4.domain.repository.DropitRepository
import com.ddd4.domain.usecases.dropit.GetDummyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {

    @Provides
    @ActivityScoped
    fun provideDummyUseCase(dropitRepository: DropitRepository): GetDummyUseCase {
        return GetDummyUseCase(dropitRepository)
    }
}