package com.ddd4.dropit.presentation.ui.folder

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddd4.dropit.domain.usecase.GetFolderUseCase
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.util.SingleLiveEvent
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.presentation.mapper.mapToPresentation
import kotlinx.coroutines.launch
import java.util.*

class FolderViewModel @ViewModelInject constructor(
    private val getFolderUseCase: GetFolderUseCase
): BaseViewModel() {

    private val _folderItems = MutableLiveData<List<PresentationEntity.Folder>>()
    val folderItems: LiveData<List<PresentationEntity.Folder>> = _folderItems

    private val _folderItemSorting = MutableLiveData<Boolean>()
    val folderItemSorting: LiveData<Boolean> = _folderItemSorting

    val sortByLatestButtonClicked = SingleLiveEvent<Void>()
    val sortByExpirationButtonClicked = SingleLiveEvent<Void>()
    val floatingButtonClicked = SingleLiveEvent<Void>()

    val folderItemClicked = SingleLiveEvent<Long>()


    init {
        getFolderItems()
        sortByLatestButtonClick()
    }

    private fun getFolderItems(){
        //TEST
        _folderItems.value =
            listOf(PresentationEntity.Folder(
                id = 0,
                name = "test",
                thumbnail = "https://bit.ly/2DCfOXL",
                createAt = Date()),

            PresentationEntity.Folder(
                id = 1,
                name = "test2",
                thumbnail = "https://bit.ly/2DCfOXL",
                createAt = Date()))
    }

    fun sortByLatestButtonClick() {
        sortByLatestButtonClicked.call()
        _folderItemSorting.value = true
    }

    fun sortByExpirationButtonClick() {
        sortByExpirationButtonClicked.call()
        _folderItemSorting.value = false
    }

    fun floatingButtonClicked(){
        floatingButtonClicked.call()
    }

}