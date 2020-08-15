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
import com.ddd4.dropit.presentation.entity.PresentationEntity.Folder
import com.ddd4.dropit.presentation.mapper.mapToPresentation
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class FolderViewModel @ViewModelInject constructor(
    private val getFolderUseCase: GetFolderUseCase
): BaseViewModel() {

    private val _folderItems = MutableLiveData<List<Folder>>()


    private val _folderItemSorting = MutableLiveData<Boolean>()
    val folderItemSorting: LiveData<Boolean> = _folderItemSorting

    private val _isButtonActivated = MutableLiveData<Boolean>()
    val isButtonActivated: LiveData<Boolean> = _isButtonActivated

    private val _selectedImageList = MutableLiveData<ArrayList<Long>>()

    private val _sortByLatestButton = SingleLiveEvent<Void>()
    val sortByLatestButton: LiveData<Void> = _sortByLatestButton

    private val _sortByExpirationButton = SingleLiveEvent<Void>()
    val sortByExpirationButton:LiveData<Void> = _sortByExpirationButton

    private val _floatingButton = SingleLiveEvent<Void>()
    val floatingButton:LiveData<Void> = _floatingButton

    private val _nextButton = SingleLiveEvent<ArrayList<Long>>()
    val nextButton: LiveData<ArrayList<Long>> = _nextButton

    private val _selectImageButton = SingleLiveEvent<String>()
    val selectImageButton: SingleLiveEvent<String> = _selectImageButton

    private val _selectedImageState = MutableLiveData<Boolean>()
    val selectedImageState: LiveData<Boolean> = _selectedImageState

    private val _item = SingleLiveEvent<Folder>()
    val item: SingleLiveEvent<Folder> = _item

    init {
        initView()
        getFolderItems()
        initView()
        sortByLatestButtonClick()
    }

    private fun initView(){
        _selectedImageState.value = false
        _selectImageButton.value = "선택"
        _selectedImageList.value = arrayListOf()
    }

    private fun getFolderItems(){
        //TEST
        _folderItems.value =
            listOf(
                Folder(
                id = 0,
                name = "D-12",
                thumbnail = "https://bit.ly/2DCfOXL",
                createAt = Date()),

            Folder(
                id = 1,
                name = "D-12",
                thumbnail = "https://bit.ly/2DCfOXL",
                createAt = Date())
            )
    }

    fun sortByLatestButtonClick() {
        _sortByLatestButton.call()
        _folderItemSorting.value = true
    }

    fun sortByExpirationButtonClick() {
        _sortByExpirationButton.call()
        _folderItemSorting.value = false
    }

    fun floatingButtonClick(){
        _floatingButton.call()
    }

    fun selectImageButtonClick() {
        if (_selectedImageState.value!!) {
            _selectImageButton.value = "선택"
        } else {
            _selectImageButton.value = "취소"
        }
        _selectedImageState.value = !_selectedImageState.value!!

    fun nextButtonClicked(){
       if(_isButtonActivated.value!!) {
           _nextButton.value = _selectedImageList.value //call
       }
    }

    val onItemClickListener by lazy {
        object : ItemHandler {
            override fun <T> onItemClicked(item: T, visibility: Boolean) {
                if (selectImageButton.value == "취소") {
                    when(visibility) {
                        true -> {
                            _selectedImageList.value?.add((item as Folder).id)
                        }
                        false -> {
                            _selectedImageList.value?.remove((item as Folder).id)
                        }
                    }
                } else {
                    _selectedImageList.value?.clear()
                }
                _isButtonActivated.value = _selectedImageList.value?.isNotEmpty()
            }

            override fun <T> onItemDetailClicked(item: T) {
                _item.value = (item as Folder)
            }
        }
    }
}