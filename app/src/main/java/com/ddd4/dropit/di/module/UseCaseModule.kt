package com.ddd4.dropit.di.module

import com.ddd4.domain.repository.DropitRepository
import com.ddd4.domain.usecases.usecase.GetDummyUseCase
import com.ddd4.domain.usecases.usecaseImpl.GetDummyUseCaseImpl
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
        return GetDummyUseCaseImpl(dropitRepository)
    }
}