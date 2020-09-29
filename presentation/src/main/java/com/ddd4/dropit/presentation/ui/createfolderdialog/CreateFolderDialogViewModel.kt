package com.ddd4.dropit.presentation.ui.createfolderdialog

import android.app.Presentation
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddd4.dropit.domain.usecase.CreateFolderUseCase
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.getValue
import com.ddd4.dropit.domain.usecase.GetFolderByNameUseCase
import com.ddd4.dropit.domain.usecase.UpdateItemByFolderIdUseCase
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.mapper.mapToDomain
import com.ddd4.dropit.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import java.util.*

class CreateFolderDialogViewModel @ViewModelInject constructor(
    private val createFolderUseCase: CreateFolderUseCase,
    private val getFolderByNameUseCase: GetFolderByNameUseCase,
    private val updateItemByFolderIdUseCase: UpdateItemByFolderIdUseCase
)
: BaseViewModel() {

    private val _cancelButton = SingleLiveEvent<Void>()
    val cancelButton: LiveData<Void> = _cancelButton

    private val _confirmButton =SingleLiveEvent<Void>()
    val confirmButton: LiveData<Void> = _confirmButton

    private val _itemIdList = MutableLiveData<ArrayList<Long>>()
    val itemIdList: LiveData<ArrayList<Long>> = _itemIdList

    val createFolder = MutableLiveData<String>()

    fun start(idList: ArrayList<Long>) = viewModelScope.launch {
        _itemIdList.value = idList
    }

    fun confirmButtonClick() = viewModelScope.launch {
        createFolder.value?.let {
            try {
                val folder = PresentationEntity.Folder(
                    id = null,
                    name = it,
                    createAt = Date()
                ).mapToDomain()

                createFolderUseCase(folder)
                getFolderIdByName()

            } catch (e: Exception) {
                Timber.d(e)
            }
        }
    }

    private fun getFolderIdByName() = viewModelScope.launch {
        try {
            createFolder.value?.let { folder ->
                _itemIdList.value?.map { id ->
                    updateItemByFolderIdUseCase(
                        getFolderByNameUseCase(folder).getValue().id!!,
                        id
                    )
                }
            }
            _confirmButton.call()

        } catch (e: Exception) {
            Timber.d(e)
        }
    }

    fun cancelButtonClick() {
        _cancelButton.call()
    }
}