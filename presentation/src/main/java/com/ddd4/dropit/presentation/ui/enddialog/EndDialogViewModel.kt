package com.ddd4.dropit.presentation.ui.enddialog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.usecase.DeleteItemUseCase
import com.ddd4.dropit.domain.usecase.GetDetailItemUseCase
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.mapper.mapToPresentation
import com.ddd4.dropit.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class EndDialogViewModel @ViewModelInject constructor(
    private val getDetailItemUseCase: GetDetailItemUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
): BaseViewModel() {


    private val _itemDetails = MutableLiveData<PresentationEntity.Item>()
    val itemDetails: LiveData<PresentationEntity.Item> = _itemDetails

    private val _itemName = MutableLiveData<String>()
    val itemName: LiveData<String> = _itemName

    private val _cancelButton = SingleLiveEvent<Void>()
    val cancelButton: LiveData<Void> = _cancelButton

    private val _confirmButton = SingleLiveEvent<Void>()
    val confirmButton: LiveData<Void> = _confirmButton

    fun start(itemId: Long) = viewModelScope.launch {
        when(val result = getDetailItemUseCase(itemId)) {
            is Result.Success -> {
                _itemDetails.value = result.data.mapToPresentation()

            }
            is Result.Error -> Timber.d(result.exception)
        }
        _itemName.value = _itemDetails.value!!.name

    }

    fun cancelButtonClick() {
        _cancelButton.call()
    }

    fun confirmButtonClick() = viewModelScope.launch {
        deleteItemUseCase(_itemDetails.value?.id!!)
        _confirmButton.call()
    }

}