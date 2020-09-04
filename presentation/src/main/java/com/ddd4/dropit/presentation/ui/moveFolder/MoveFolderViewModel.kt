package com.ddd4.dropit.presentation.ui.moveFolder

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.usecase.GetFolderUseCase
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.entity.PresentationEntity.Folder
import com.ddd4.dropit.presentation.mapper.mapToPresentation
import com.ddd4.dropit.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class MoveFolderViewModel @ViewModelInject constructor(
    private val getFolderUseCase: GetFolderUseCase
): BaseViewModel() {

    private val _folderList = MutableLiveData<List<Folder>>()
    val folderList: LiveData<List<Folder>> = _folderList

    private val _newFolderButton = SingleLiveEvent<Void>()
    val newFolderButton: LiveData<Void> = _newFolderButton

    fun start() = viewModelScope.launch {
        when (val result = getFolderUseCase()){
            is Result.Success -> {
                _folderList.value =
                    if (result.data.size == 1 && result.data[0].name == "최근항목") {
                        emptyList()
                    } else {
                        result.data.map(DomainEntity.Folder::mapToPresentation)
                    }
            }
            is Result.Error -> Timber.d(result.exception)
        }
    }

    fun createNewFolder(){
        _newFolderButton.call()
    }
}