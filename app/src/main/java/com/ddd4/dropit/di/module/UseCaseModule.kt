package com.ddd4.dropit.di.module

import com.ddd4.dropit.domain.repository.DropitRepository
import com.ddd4.dropit.domain.usecase.GetFolderUseCase
import com.ddd4.dropit.domain.usecase.GetItemUseCase
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
    fun provideFolderUseCase(dropitRepository: DropitRepository): GetFolderUseCase {
        return GetFolderUseCase(dropitRepository)
    }

    @Provides
    @Singleton
    fun provideItemUseCase(dropitRepository: DropitRepository): GetItemUseCase {
        return GetItemUseCase(dropitRepository)
    }
}