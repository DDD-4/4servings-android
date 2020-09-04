package com.ddd4.dropit.di.module

import androidx.room.Update
import com.ddd4.dropit.domain.repository.DropitRepository
import com.ddd4.dropit.domain.usecase.*
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
    fun provideSectionUseCase(dropitRepository: DropitRepository): SetSectionUseCase =
        SetSectionUseCase(dropitRepository)

    @Provides
    @Singleton
    fun provideCategoryUseCase(dropitRepository: DropitRepository): GetCategoryUseCase =
        GetCategoryUseCase(dropitRepository)

    @Provides
    @Singleton
    fun provideFolderUseCase(dropitRepository: DropitRepository): GetFolderUseCase =
        GetFolderUseCase(dropitRepository)

    @Provides
    @Singleton
    fun provideSetItemUseCase(dropitRepository: DropitRepository): SetItemUseCase =
        SetItemUseCase(dropitRepository)

    @Provides
    @Singleton
    fun provideSubCategoryUseCase(dropitRepository: DropitRepository): GetSubCategoryUseCase =
        GetSubCategoryUseCase(dropitRepository)

    @Provides
    @Singleton
    fun provideGetAlarmIdUseCase(dropitRepository: DropitRepository): GetAlarmIdUseCase =
        GetAlarmIdUseCase(dropitRepository)

    @Provides
    @Singleton
    fun provideGetFolderItemUseCase(dropitRepository: DropitRepository): GetFolderItemUseCase =
        GetFolderItemUseCase(dropitRepository)

    @Provides
    @Singleton
    fun provideGetDetailItemUseCase(dropitRepository: DropitRepository): GetDetailItemUseCase =
        GetDetailItemUseCase(dropitRepository)

    @Provides
    @Singleton
    fun provideUpdateItemUseCase(dropitRepository: DropitRepository): UpdateItemUseCase =
        UpdateItemUseCase(dropitRepository)
}