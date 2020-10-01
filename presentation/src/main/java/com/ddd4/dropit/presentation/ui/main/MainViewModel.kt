package com.ddd4.dropit.presentation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.usecase.GetCategoryUseCase
import com.ddd4.dropit.domain.usecase.GetFoldersUseCase
import com.ddd4.dropit.domain.usecase.SetSectionUseCase
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.mapper.mapToPresentation
import com.ddd4.dropit.presentation.util.ItemClickListener
import com.ddd4.dropit.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getFoldersUseCase: GetFoldersUseCase
): BaseViewModel() {

    private val _categoryItems = MutableLiveData<List<PresentationEntity.Category>>()
    val categoryItems : LiveData<List<PresentationEntity.Category>> = _categoryItems

    private val _folderItems = MutableLiveData<List<PresentationEntity.Folder>>()
    val folderItems : LiveData<List<PresentationEntity.Folder>> = _folderItems

    private val _folderState = MutableLiveData<Boolean>(false)
    val folderState : LiveData<Boolean> = _folderState

    val categoryClick = SingleLiveEvent<Long>()

    val folderClick = SingleLiveEvent<Long>()

    val addClick = SingleLiveEvent<Void>()

    init {
        getCategoryItems()
    }

    private fun getCategoryItems() {
        viewModelScope.launch {
            when (val result = getCategoryUseCase()) {
                is Result.Success -> _categoryItems.value = result.data.map(DomainEntity.Category::mapToPresentation)
                is Result.Error -> Timber.d(result.exception)
            }
        }
    }

    fun getFolderItems() {
        viewModelScope.launch {
            when (val result = getFoldersUseCase()) {
                is Result.Success -> {
                    if (result.data.isNotEmpty()) {
                        _folderItems.value = result.data.map(DomainEntity.Folder::mapToPresentation)
                        _folderState.value = true
                    } else {
                        _folderState.value = false
                    }
                }
                is Result.Error -> Timber.d(result.exception)
            }
        }
    }

    val onItemClickListener = object: ItemClickListener {
        override fun <T> onItemClicked(item: T) {
            when (item) {
                is PresentationEntity.Folder -> folderClick.value = item.id
                is PresentationEntity.Category -> categoryClick.value = item.id
            }
        }
    }

    fun onAddClicked() {
        addClick.call()
    }
}