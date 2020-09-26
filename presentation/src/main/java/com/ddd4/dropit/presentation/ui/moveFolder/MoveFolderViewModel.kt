package com.ddd4.dropit.presentation.ui.moveFolder

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.usecase.GetFoldersUseCase
import com.ddd4.dropit.domain.usecase.UpdateItemByFolderIdUseCase
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.entity.PresentationEntity.Folder
import com.ddd4.dropit.presentation.mapper.mapToPresentation
import com.ddd4.dropit.presentation.util.ItemClickListener
import com.ddd4.dropit.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class MoveFolderViewModel @ViewModelInject constructor(
    private val getFoldersUseCase: GetFoldersUseCase,
    private val updateItemByFolderIdUseCase: UpdateItemByFolderIdUseCase
): BaseViewModel() {

    private val _folderList = MutableLiveData<List<Folder>>()
    val folderList: LiveData<List<Folder>> = _folderList

    private val _itemIdList = MutableLiveData<ArrayList<Long>>()
    val itemIdList: LiveData<ArrayList<Long>> = _itemIdList

    private val _moveFolder = SingleLiveEvent<Void>()
    val moveFolder: LiveData<Void> = _moveFolder

    private val _newFolderButton = SingleLiveEvent<Void>()
    val newFolderButton: LiveData<Void> = _newFolderButton

    private val _backButton = SingleLiveEvent<Void>()
    val backButton: LiveData<Void> = _backButton

    fun start(idList: ArrayList<Long>) = viewModelScope.launch {
        when (val result = getFoldersUseCase()){
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
        
        _itemIdList.value = idList
        Timber.e("pass: ${ _itemIdList.value } ")
    }

    fun createNewFolder(){
        _newFolderButton.call()
    }

    fun backButtonClick(){
        _backButton.call()
    }

    val onItemClickListener by lazy {
        object : ItemClickListener {
            override fun <T> onItemClicked(item: T) {
               viewModelScope.launch {
                   _itemIdList.value?.map { itemId ->
                       Timber.e("listener: $itemId, ${(item as Folder).id!!}")
                       updateItemByFolderIdUseCase((item as Folder).id!!, itemId)
                   }
                   _moveFolder.call()
               }
            }
        }
    }
}