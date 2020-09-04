package com.ddd4.dropit.presentation.ui.detailFolder

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddd4.dropit.domain.usecase.GetDetailItemUseCase
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.presentation.mapper.mapToPresentation
import com.ddd4.dropit.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class FolderItemDetailViewModel @ViewModelInject constructor(
    private val getDetailItemUseCase: GetDetailItemUseCase
): BaseViewModel() {

    private val _itemDetails = MutableLiveData<PresentationEntity.Item>()
    val itemDetails: LiveData<PresentationEntity.Item> = _itemDetails

    val itemName = MutableLiveData<String>()

    private val _textIsEmpty = SingleLiveEvent<Void>()
    val testIsEmpty: LiveData<Void> = _textIsEmpty

    private val _isEditMode = MutableLiveData(false)
    val isEditMode: LiveData<Boolean> = _isEditMode

    fun start(itemId: Long) = viewModelScope.launch {
        when(val result = getDetailItemUseCase(itemId)) {
            is Result.Success -> _itemDetails.value = result.data.mapToPresentation()
            is Result.Error -> Timber.d(result.exception)
        }
        itemName.value = _itemDetails.value!!.name
    }

    fun editButtonClick() {
        if(itemName.value!!.isNotEmpty())
        _isEditMode.value = !_isEditMode.value!!
        else _textIsEmpty.call()

    }

}