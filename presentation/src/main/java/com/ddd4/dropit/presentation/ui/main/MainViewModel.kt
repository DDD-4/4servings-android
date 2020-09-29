package com.ddd4.dropit.presentation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.getValue
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
            try {
                _categoryItems.value =
                    getCategoryUseCase().getValue().map(DomainEntity.Category::mapToPresentation)
            } catch (e: Exception) {
                Timber.d(e)
            }
        }
    }

    fun getFolderItems() {
        viewModelScope.launch {
            try {
                val items = getFoldersUseCase().getValue()
                if(items.isNotEmpty()) {
                    _folderItems.value = items.map(DomainEntity.Folder::mapToPresentation)
                    _folderState.value = true
                } else {
                    _folderState.value = false
                }
            } catch (e: Exception) {
                _folderItems.value = emptyList()

                Timber.d(e)
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