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

    val sortByLatestButton = SingleLiveEvent<Void>()
    val sortByExpirationButton = SingleLiveEvent<Void>()
    val floatingButton = SingleLiveEvent<Void>()
    val nextButton = SingleLiveEvent<Void>()


    private val _selectImageButton = SingleLiveEvent<String>()
    val selectImageButton: SingleLiveEvent<String> = _selectImageButton

    private val _selectedImageState = MutableLiveData<Boolean>()
    val selectedImageState: LiveData<Boolean> = _selectedImageState

    val folderItem = SingleLiveEvent<Long>()


    init {
        initView()
        getFolderItems()
        sortByLatestButtonClick()
    }

    private fun initView(){
        _selectedImageState.value = false
        _selectImageButton.value = "선택"
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
        sortByLatestButton.call()
        _folderItemSorting.value = true
    }

    fun sortByExpirationButtonClick() {
        sortByExpirationButton.call()
        _folderItemSorting.value = false
    }

    fun floatingButtonClicked(){
        floatingButton.call()
    }

    fun selectImageButtonClicked() {
        if (_selectedImageState.value!!) {
            _selectImageButton.value = "선택"
        } else {
            _selectImageButton.value = "취소"
        }
        _selectedImageState.value = !_selectedImageState.value!!
    }

    fun nextButtonClicked(){
        nextButton.call()
    }

}