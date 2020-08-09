package com.ddd4.dropit.di.module

import com.ddd4.dropit.domain.repository.DropitRepository
import com.ddd4.dropit.domain.usecase.getFolderUseCase
import com.ddd4.dropit.domain.usecase.getItemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideFolderUseCase(dropitRepository: DropitRepository): getFolderUseCase {
        return getFolderUseCase(dropitRepository)
    }

    @Provides
    @Singleton
    fun provideItemUseCase(dropitRepository: DropitRepository): getItemUseCase {
        return getItemUseCase(dropitRepository)
    }
}