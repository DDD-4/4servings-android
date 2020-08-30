package com.ddd4.dropit.presentation.ui.add

import android.app.DatePickerDialog
import android.text.Editable
import android.text.TextWatcher
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.usecase.GetCategoryUseCase
import com.ddd4.dropit.domain.usecase.GetSubCategoryUseCase
import com.ddd4.dropit.domain.usecase.SetItemUseCase
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.mapper.mapToDomain
import com.ddd4.dropit.presentation.mapper.mapToPresentation
import com.ddd4.dropit.presentation.util.ItemClickListener
import com.ddd4.dropit.presentation.util.SingleLiveEvent
import com.ddd4.dropit.presentation.util.addToDate
import com.ddd4.dropit.presentation.util.intToDate
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class AddSharedViewModel @ViewModelInject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getSubCategoryUseCase: GetSubCategoryUseCase,
    private val setItemUseCase: SetItemUseCase
) : BaseViewModel() {

    private val _progressValue = MutableLiveData<Int>()
    val progressValue: LiveData<Int> = _progressValue

    private val _categoryItems = MutableLiveData<List<PresentationEntity.Category>>()
    val categoryItems: LiveData<List<PresentationEntity.Category>> = _categoryItems

    private val _subCategoryItems = MutableLiveData<List<PresentationEntity.SubCategory>>()
    val subCategoryItems: LiveData<List<PresentationEntity.SubCategory>> = _subCategoryItems

    private val _nextButtonState = MutableLiveData<Boolean>()
    val nextButtonState: LiveData<Boolean> = _nextButtonState

    private val _selectedCategory = MutableLiveData<Long>()
    private val _selectedSubCategory = MutableLiveData<Long>()
    private val _selectedName = MutableLiveData<String>()
    //private val _selectedImage = MutableLiveData<String>()
    private val _selectedStartAt = MutableLiveData<Date>()
    private val _selectedEndAt = MutableLiveData<Date>()

    private val _dayOfEnd = MutableLiveData<Int>()

    private val _pickerDate = MutableLiveData<String>()
    val pickerDate : LiveData<String> = _pickerDate

    private val _isDateState = MutableLiveData<Boolean>()
    val isDateState: LiveData<Boolean> = _isDateState

    val nextClick = SingleLiveEvent<Void>()

    val addComplete = SingleLiveEvent<Void>()

    fun setProgressValue(value: Int) {
        _progressValue.value = value
    }

    fun getCategoryItems() {
        viewModelScope.launch {
            when (val result = getCategoryUseCase.execute()) {
                is Result.Success -> {
                    if (result.data.isNotEmpty()) {
                        _categoryItems.value = result.data.map(DomainEntity.Category::mapToPresentation)
                    } else {
                        Timber.d("empty")
                    }
                }
                is Result.Error -> Timber.d(result.exception)
            }
        }
    }

    private fun getSubCategoryItems(id: Long) {
        viewModelScope.launch {
            when (val result = getSubCategoryUseCase.execute(id)) {
                is Result.Success -> {
                    if (result.data.isNotEmpty()) {
                        _subCategoryItems.value = result.data.map(DomainEntity.SubCategory::mapToPresentation)
                    } else {
                        Timber.d("empty")
                    }
                }
                is Result.Error ->  Timber.d(result.exception)
            }
        }
    }

    fun setItem() {
        viewModelScope.launch {
            when (val result = setItemUseCase.execute(PresentationEntity.Item(
                    categoryId = _selectedCategory.value!!,
                    subCategoryId = _selectedSubCategory.value!!,
                    name = _selectedName.value!!,
                    image = "image",
                    startAt = _selectedStartAt.value!!,
                    endAt = _selectedEndAt.value!!,
                    createAt = Date()).mapToDomain())) {
                is Result.Success -> addComplete.call()
                is Result.Error -> Timber.d(result.exception)
            }
        }
    }

    val nameTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            _selectedName.value = s.toString()
        }
        override fun afterTextChanged(s: Editable?) {
            _nextButtonState.value = !s.isNullOrEmpty()
        }
    }

    fun onImageClicked() {
        //add Image -> Permission & On/Off
    }

    val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        //Timber.d("Date: $year / ${month+1} / $dayOfMonth")
        _pickerDate.value = "${year}년 ${month+1}월 ${dayOfMonth}일"
        _selectedStartAt.value = intToDate(year, month, dayOfMonth)
        _selectedEndAt.value = addToDate(year, month, dayOfMonth, _dayOfEnd.value!!)
        _isDateState.value = true
        _nextButtonState.value = true
    }

    val onItemClickListener = object: ItemClickListener {
        override fun <T> onItemClicked(item: T) {
            when (item) {
                is PresentationEntity.Category -> {
                    _nextButtonState.value = false
                    _selectedCategory.value = item.id
                    getSubCategoryItems(item.id)
                }
                is PresentationEntity.SubCategory -> {
                    _selectedSubCategory.value = item.id
                    _dayOfEnd.value = item.endAt
                    _nextButtonState.value = true
                }
            }
        }
    }

    fun onNextClicked() {
        nextClick.call()
        _nextButtonState.value = false
    }
}