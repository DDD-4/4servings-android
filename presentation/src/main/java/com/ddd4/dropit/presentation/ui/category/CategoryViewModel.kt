package com.ddd4.dropit.presentation.ui.category

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.usecase.GetCategoryByIdUseCase
import com.ddd4.dropit.domain.usecase.GetCategoryItemUseCase
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.mapper.mapToPresentation
import com.ddd4.dropit.presentation.ui.folder.ItemHandler
import com.ddd4.dropit.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.ArrayList

class CategoryViewModel @ViewModelInject constructor(
    private val getCategoryItemUseCase: GetCategoryItemUseCase,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase
): BaseViewModel() {

    private val _categoryItems = MutableLiveData<List<PresentationEntity.Item>>()
    private val _sortByExpirationButton = SingleLiveEvent<Void>()
    private val _selectedImageList = MutableLiveData<ArrayList<Long>>()
    private val _sortByLatestButton = SingleLiveEvent<Void>()
    private val _categoryItemSorting = MutableLiveData<Boolean>()

    private val _categoryName = MutableLiveData<String>()
    val categoryName: LiveData<String> = _categoryName

    private val _isButtonActivated = MutableLiveData<Boolean>()
    val isButtonActivated: LiveData<Boolean> = _isButtonActivated

    private val _backButton = SingleLiveEvent<Void>()
    val backButton: LiveData<Void> = _backButton

    private val _floatingButton = SingleLiveEvent<Void>()
    val floatingButton: LiveData<Void> = _floatingButton

    private val _nextButton = SingleLiveEvent<ArrayList<Long>>()
    val nextButton: LiveData<ArrayList<Long>> = _nextButton

    private val _selectImageButton = SingleLiveEvent<String>()
    val selectImageButton: SingleLiveEvent<String> = _selectImageButton

    private val _selectedImageState = MutableLiveData<Boolean>()
    val selectedImageState: LiveData<Boolean> = _selectedImageState

    private val _item = SingleLiveEvent<Long>()
    val item: SingleLiveEvent<Long> = _item

    init {
        initView()
        sortByLatestButtonClick()
    }

    //TODO FIX
    private fun initView() {
        _selectedImageState.value = false
        _selectImageButton.value = "선택"
        _selectedImageList.value = arrayListOf()
        _isButtonActivated.value = false
    }

    fun start(categoryId: Long) = viewModelScope.launch {
        when (val result = getCategoryItemUseCase(categoryId)) {
            is Result.Success -> {
                if (result.data.isNotEmpty()) {
                    _categoryItems.value = result.data.map(DomainEntity.Item::mapToPresentation).sortedByDescending { it.endAt.time }
                } else {
                    _categoryItems.value = emptyList()
                    Timber.d("empty")
                }
            }
            is Result.Error -> Timber.d(result.exception)
        }

        when(val result = getCategoryByIdUseCase(categoryId)) {
            is Result.Success -> _categoryName.value = result.data.title
            is Result.Error -> Timber.d(result.exception)
        }
    }

    fun sortByLatestButtonClick() {
        _categoryItems.value = _categoryItems.value?.sortedByDescending { it.endAt.time }
        _categoryItemSorting.value = true
        _sortByLatestButton.call()
    }

    fun sortByExpirationButtonClick() {
        _categoryItems.value = _categoryItems.value?.sortedBy{ it.endAt.time }
        _categoryItemSorting.value = false
        _sortByExpirationButton.call()
    }

    fun floatingButtonClick() {
        _floatingButton.call()
    }

    //TODO FIX
    fun selectImageButtonClick() {
        if (_selectedImageState.value!!) {
            _selectImageButton.value = "선택"
        } else {
            _selectImageButton.value = "취소"
        }
        _selectedImageState.value = !_selectedImageState.value!!
    }

    fun nextButtonClicked() {
        if (_isButtonActivated.value!!) {
            _nextButton.value = _selectedImageList.value //call
        }
    }

    fun backButtonClick(){
        _backButton.call()
    }

    //TODO FIX
    val onItemClickListener by lazy {
        object : ItemHandler {
            override fun <T> onItemClicked(item: T, visibility: Boolean) {
                if (selectImageButton.value == "취소") {
                    when (visibility) {
                        true -> {
                            _selectedImageList.value?.add((item as PresentationEntity.Item).id!!)
                        }
                        false -> {
                            _selectedImageList.value?.remove((item as PresentationEntity.Item).id)
                        }
                    }
                } else {
                    _selectedImageList.value?.clear()
                }
                _isButtonActivated.value = _selectedImageList.value?.isNotEmpty()
            }

            override fun <T> onItemDetailClicked(item: T) {
                _item.value = (item as PresentationEntity.Item).id
            }
        }
    }
}