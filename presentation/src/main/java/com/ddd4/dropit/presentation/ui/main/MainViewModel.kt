package com.ddd4.dropit.presentation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ddd4.dropit.domain.usecase.getFolderUseCase
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.mapper.mapToPresentation
import com.ddd4.dropit.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(
    private val getFolderUseCase: getFolderUseCase
): BaseViewModel() {

    private val _folderItems = MutableLiveData<List<PresentationEntity.Folder>>()
    val folderItems : LiveData<List<PresentationEntity.Folder>> = _folderItems

    val startFolder = SingleLiveEvent<Long>()
    val startCategory = SingleLiveEvent<Long>()

    init {
        getFolderItems()
    }

    private fun getFolderItems() {
        viewModelScope.launch {
            getFolderUseCase.execute().also { result ->
                when (result) {
                    is getFolderUseCase.Result.Success -> {
                        if (result.data.isNotEmpty()) {
                            _folderItems.value = result.data.map {
                                it.mapToPresentation()
                            }
                        } else {
                            //Empty

                        }
                    }
                    is getFolderUseCase.Result.Error -> {
                        //Error

                    }
                }
            }
        }
    }
}