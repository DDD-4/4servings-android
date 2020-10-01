package com.ddd4.dropit.presentation.ui.detailfolder

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddd4.dropit.domain.usecase.GetDetailItemUseCase
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.getValue
import com.ddd4.dropit.domain.usecase.DeleteItemUseCase
import com.ddd4.dropit.domain.usecase.UpdateItemUseCase
import com.ddd4.dropit.presentation.mapper.mapToDomain
import com.ddd4.dropit.presentation.mapper.mapToPresentation
import com.ddd4.dropit.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class FolderItemDetailViewModel @ViewModelInject constructor(
    private val getDetailItemUseCase: GetDetailItemUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val updateItemUseCase: UpdateItemUseCase
): BaseViewModel() {

    private val _itemDetails = MutableLiveData<PresentationEntity.Item>()
    val itemDetails: LiveData<PresentationEntity.Item> = _itemDetails

    val itemName = MutableLiveData<String>()

    private val _textIsEmpty = SingleLiveEvent<Void>()
    val testIsEmpty: LiveData<Void> = _textIsEmpty

    private val _isEditMode = MutableLiveData(false)
    val isEditMode: LiveData<Boolean> = _isEditMode

    private val _editCancelButton = SingleLiveEvent<Void>()
    val editCancelButton: LiveData<Void> = _editCancelButton

    private val _editCompleteButton = SingleLiveEvent<Void>()
    val editCompleteButton: LiveData<Void> = _editCompleteButton

    private val _itemDeleteButton = SingleLiveEvent<Void>()
    val itemDeleteButton: LiveData<Void> = _itemDeleteButton

    private val _backButton = SingleLiveEvent<Void>()
    val backButton: LiveData<Void> = _backButton

    fun start(itemId: Long) = viewModelScope.launch {
        try {
            getDetailItemUseCase(itemId)
                .getValue()
                .mapToPresentation()
                .let { item ->
                    _itemDetails.value = item
                    itemName.value = item.name
                }
        } catch (e: Exception) {
            Timber.d(e)
        }
    }

    fun editButtonClick() = viewModelScope.launch  {
        if(itemName.value!!.isNotEmpty()){
            _isEditMode.value = !_isEditMode.value!!

            when(_isEditMode.value){
                false -> {
                    _itemDetails.value?.name = itemName.value!!
                    updateItemUseCase(_itemDetails.value!!.mapToDomain())
                    _editCompleteButton.call()
                }
            }
        }
        else _textIsEmpty.call()
    }

    fun deleteButtonClick() = viewModelScope.launch {
        if(_isEditMode.value == true){ //cancel
            itemName.value = _itemDetails.value?.name!!
            _isEditMode.value = !_isEditMode.value!!
            _editCancelButton.call()
        } else {
            deleteItemUseCase(_itemDetails.value?.id!!)
            _itemDeleteButton.call()
        }
    }

    fun backButtonClick() {
        _backButton.call()
    }
}