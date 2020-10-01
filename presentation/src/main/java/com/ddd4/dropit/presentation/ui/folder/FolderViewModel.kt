package com.ddd4.dropit.presentation.ui.folder

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.util.SingleLiveEvent
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.getValue
import com.ddd4.dropit.domain.usecase.GetFolderByIdUseCase
import com.ddd4.dropit.domain.usecase.GetFolderItemUseCase
import com.ddd4.dropit.presentation.entity.PresentationEntity.*
import com.ddd4.dropit.presentation.mapper.mapToPresentation
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class FolderViewModel @ViewModelInject constructor(
    private val getFolderItemUseCase: GetFolderItemUseCase,
    private val getFolderByIdUseCase: GetFolderByIdUseCase
): BaseViewModel() {

    private val _selectedImageList = ArrayList<Long>()
    private val _sortByLatestButton = SingleLiveEvent<Void>()
    private val _sortByExpirationButton = SingleLiveEvent<Void>()

    private val _folderItems = MutableLiveData<List<Item>>()
    val folderItems: LiveData<List<Item>> = _folderItems

    private val _folderName = MutableLiveData<String>()
    val folderName: LiveData<String> = _folderName

    private val _folderItemSorting = MutableLiveData<Boolean>()
    val folderItemSorting: LiveData<Boolean> = _folderItemSorting

    private val _isButtonActivated = MutableLiveData<Boolean>()
    val isButtonActivated: LiveData<Boolean> = _isButtonActivated

    private val _floatingButton = SingleLiveEvent<Void>()
    val floatingButton: LiveData<Void> = _floatingButton

    private val _backButton = SingleLiveEvent<Void>()
    val backButton: LiveData<Void> = _backButton

    private val _nextButton = SingleLiveEvent<ArrayList<Long>>()
    val nextButton: LiveData<ArrayList<Long>> = _nextButton

    private val _selectedImageState = MutableLiveData<Boolean>()
    val selectedImageState: LiveData<Boolean> = _selectedImageState

    private val _item = SingleLiveEvent<Long>()
    val item: SingleLiveEvent<Long> = _item

    val clearSelected = SingleLiveEvent<Void>()

    /**
     * false : 취소,
     * true : 선택
     */
    private val _selectImageMode = MutableLiveData<Boolean>()
    val selectImageMode: LiveData<Boolean> = _selectImageMode

    init {
        initView()
        sortByLatestButtonClick()
    }

    //TODO FIX
    private fun initView() {
        _selectedImageState.value = false
        _selectImageMode.value = true
        _isButtonActivated.value = false
    }

    fun start(folderId: Long) = viewModelScope.launch {
        try {
            val items = getFolderItemUseCase(folderId).getValue()
            if(items.isNotEmpty()) {
                _folderItems.value =  getFolderItemUseCase(folderId)
                        .getValue()
                        .map(DomainEntity.Item::mapToPresentation)
                        .sortedByDescending { it.endAt.time }
            } else {
                _folderItems.value = emptyList()
            }

            _folderName.value = getFolderByIdUseCase(folderId).getValue().name

        } catch (e: Exception) {
            Timber.d(e)
        }
    }

    fun sortByLatestButtonClick() {
        _folderItems.value?.let { items ->
            _folderItems.value = items.sortedByDescending { it.endAt.time }
        }
        _folderItemSorting.value = true
        _sortByLatestButton.call()
    }

    fun sortByExpirationButtonClick() {
        _folderItems.value = _folderItems.value!!.sortedBy { it.endAt.time }
        _folderItemSorting.value = false
        _sortByExpirationButton.call()
    }

    fun floatingButtonClick() {
        _floatingButton.call()
    }

    fun selectImageButtonClick() {
        _selectImageMode.value = _selectedImageState.value!!
        _selectedImageState.value = !_selectedImageState.value!!
    }

    fun nextButtonClicked() {
        if (_isButtonActivated.value!!) {
            _nextButton.value = _selectedImageList //call
        }
    }

    fun backButtonClick() {
        _backButton.call()
    }

    //TODO FIX
    val onItemClickListener by lazy {
        object : ItemHandler {
            override fun <T> onItemClicked(item: T, visibility: Boolean) {
                if (selectImageMode.value == false) {
                    when (visibility) {
                        true -> {
                            _selectedImageList.add((item as Item).id!!)
                        }
                        false -> {
                            _selectedImageList.remove((item as Item).id)
                        }
                    }
                } else {
                    Timber.d("select.")
                }
                _isButtonActivated.value = _selectedImageList.isNotEmpty()
            }

            override fun <T> onItemDetailClicked(item: T) {
                _item.value = (item as Item).id
            }
        }
    }
}