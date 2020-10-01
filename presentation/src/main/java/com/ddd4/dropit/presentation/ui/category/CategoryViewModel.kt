package com.ddd4.dropit.presentation.ui.category

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.getValue
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
    val categoryItems: LiveData<List<PresentationEntity.Item>> = _categoryItems

    private val _sortByExpirationButton = SingleLiveEvent<Void>()
    val sortByExpirationButton: SingleLiveEvent<Void> = _sortByExpirationButton

    private val _sortByLatestButton = SingleLiveEvent<Void>()
    val sortByLatestButton: LiveData<Void> = _sortByLatestButton

    private val _categoryItemSorting = MutableLiveData<Boolean>()
    val categoryItemSorting: LiveData<Boolean> = _categoryItemSorting

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

    private val _selectedImageState = MutableLiveData<Boolean>()
    val selectedImageState: LiveData<Boolean> = _selectedImageState

    private val _item = SingleLiveEvent<Long>()
    val item: SingleLiveEvent<Long> = _item

    private var _selectedImageList = ArrayList<Long>()

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
        _selectedImageList = arrayListOf()
        _isButtonActivated.value = false
    }

    fun start(categoryId: Long) = viewModelScope.launch {
        try {
            _categoryItems.value = getCategoryItemUseCase(categoryId)
                .getValue()
                .map(DomainEntity.Item::mapToPresentation)
                .sortedByDescending { it.endAt.time }

            _categoryName.value = getCategoryByIdUseCase(categoryId).getValue().title

        } catch(e: Exception) {
            _categoryItems.value = emptyList()
            _categoryName.value = ""

            Timber.d(e)
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

    fun selectImageButtonClick() {
        _selectImageMode.value = _selectedImageState.value!!
        _selectedImageState.value = !_selectedImageState.value!!
    }

    fun nextButtonClicked() {
        if (_isButtonActivated.value!!) {
            _nextButton.value = _selectedImageList //call
        }
    }

    fun backButtonClick(){
        _backButton.call()
    }

    //TODO FIX
    val onItemClickListener by lazy {
        object : ItemHandler {
            override fun <T> onItemClicked(item: T, visibility: Boolean) {
                if (selectImageMode.value == false) {
                    when (visibility) {
                        true -> {
                            _selectedImageList.add((item as PresentationEntity.Item).id!!)
                        }
                        false -> {
                            _selectedImageList.remove((item as PresentationEntity.Item).id)
                        }
                    }
                } else {
                    _selectedImageList.clear()
                }
                _isButtonActivated.value = _selectedImageList.isNotEmpty()
            }

            override fun <T> onItemDetailClicked(item: T) {
                _item.value = (item as PresentationEntity.Item).id
            }
        }
    }
}