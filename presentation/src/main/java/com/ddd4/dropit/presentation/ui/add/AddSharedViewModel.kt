package com.ddd4.dropit.presentation.ui.add

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ddd4.dropit.domain.Result
import com.ddd4.dropit.domain.entity.DomainEntity
import com.ddd4.dropit.domain.getValue
import com.ddd4.dropit.domain.usecase.GetCategoryUseCase
import com.ddd4.dropit.domain.usecase.GetSubCategoryUseCase
import com.ddd4.dropit.domain.usecase.SetItemUseCase
import com.ddd4.dropit.presentation.base.ui.BaseViewModel
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.mapper.mapToDomain
import com.ddd4.dropit.presentation.mapper.mapToPresentation
import com.ddd4.dropit.presentation.ui.add.model.AlarmModel
import com.ddd4.dropit.presentation.util.*
import com.ddd4.dropit.presentation.util.permission.PermissionHelper
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class AddSharedViewModel @ViewModelInject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getSubCategoryUseCase: GetSubCategoryUseCase,
    private val setItemUseCase: SetItemUseCase,
    private val permissionHelper: PermissionHelper
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

    private val _selectedImage = MutableLiveData<String>()
    val selectedImage : LiveData<String> = _selectedImage

    private val _selectedStartAt = MutableLiveData<Date>()
    private val _selectedEndAt = MutableLiveData<Date>()

    private val _dayOfEnd = MutableLiveData<Int>()

    private val _pickerDate = MutableLiveData<String>()
    val pickerDate : LiveData<String> = _pickerDate

    private val _isDateState = MutableLiveData<Boolean>()
    val isDateState: LiveData<Boolean> = _isDateState

    val nextClick = SingleLiveEvent<Void>()

    val isPermissionState = SingleLiveEvent<Boolean>()

    val captureClick = SingleLiveEvent<Void>()

    val addComplete = SingleLiveEvent<AlarmModel>()

    val isLittleState = MutableLiveData<Boolean>()
    val isDontState = MutableLiveData<Boolean>()
    val dateLittleText = MutableLiveData<String>()
    val dateDontText = MutableLiveData<String>()

    fun setProgressValue(value: Int) {
        _progressValue.value = value
    }

    fun onNextClicked() {
        nextClick.call()
        _nextButtonState.value = false
    }

    fun onImageClicked() {
        if (permissionHelper.isCapturePermissionState()) {
            captureClick.call()
        } else {
            isPermissionState.value = false
        }
    }

    fun startCapture() {
        captureClick.call()
    }

    fun getCategoryItems() {
        viewModelScope.launch {
            try {
                val item = getCategoryUseCase().getValue()

                if(item.isNotEmpty()) {
                    _categoryItems.value = item.map(DomainEntity.Category::mapToPresentation)
                } else {
                    Timber.d("empty")
                }
            } catch (e: Exception) {
                Timber.d(e)
            }
        }
    }

    private fun getSubCategoryItems(id: Long) {
        viewModelScope.launch {
            try {
                val item = getSubCategoryUseCase(id).getValue()

                if(item.isNotEmpty()) {
                    _subCategoryItems.value = item.map(DomainEntity.SubCategory::mapToPresentation)
                }
            } catch (e: Exception) {
                Timber.d(e)
            }
        }
    }

    fun setItem() {
        viewModelScope.launch {
            val alarmTime = _selectedEndAt.value!!.time
            when (val result = setItemUseCase(PresentationEntity.Item(
                    id = null,
                    categoryId = _selectedCategory.value!!,
                    subCategoryId = _selectedSubCategory.value!!,
                    alarmTime = alarmTime,
                    name = _selectedName.value!!,
                    image = _selectedImage.value!!,
                    startAt = _selectedStartAt.value!!,
                    endAt = _selectedEndAt.value!!,
                    createAt = Date()).mapToDomain())) {
                is Result.Success -> {
                    addComplete.value = AlarmModel(result.data, alarmTime)
                }
                is Result.Error -> Timber.d(result.exception)
            }
        }
    }

    fun onCategoryClicked(categoryId: Long) {
        _nextButtonState.value = false
        _selectedCategory.value = categoryId
        getSubCategoryItems(categoryId)
    }

    fun onSubCategoryClicked(subCategoryId: Long, endAt: Int) {
        _selectedSubCategory.value = subCategoryId
        _dayOfEnd.value = endAt
        _nextButtonState.value = true
    }

    fun setSelectedName(text: String) {
        _selectedName.value = text
        _nextButtonState.value = text.isNotEmpty() && !_selectedImage.value.isNullOrEmpty()
    }

    fun setSelectedImage(imagePath: String) {
        _selectedImage.value = imagePath
        _nextButtonState.value = !_selectedName.value.isNullOrEmpty() && !_selectedImage.value.isNullOrEmpty()
    }

    fun setKnowDate(year: Int, month: Int, dayOfMonth: Int) {
        _selectedStartAt.value = startDate(year, month, dayOfMonth)
        _selectedEndAt.value = endDate(year, month, dayOfMonth, _dayOfEnd.value!!)

        _pickerDate.value = "${year}년 ${month+1}월 ${dayOfMonth}일"

        isLittleState.value = false
        isDontState.value = false
        dateLittleText.value = ""
        _isDateState.value = true
        _nextButtonState.value = true
    }

    fun setLittleState() {
        isLittleState.value = true
        isDontState.value = false
        _pickerDate.value = ""
        _isDateState.value = false
        _nextButtonState.value = false
    }

    fun setLittleDate(week: String) {
        if (week == "") {
            getLittleDate(0)
        } else {
            getLittleDate(week.toInt())
        }
        _nextButtonState.value = week.isNotEmpty()
    }

    fun setDontState() {
        getDontDate()
        isLittleState.value = false
        isDontState.value = true
        dateLittleText.value = ""
        _pickerDate.value = ""
        _isDateState.value = false
        _nextButtonState.value = true
    }

    private fun getLittleDate(week: Int) {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.WEEK_OF_YEAR, -week)

        _selectedStartAt.value = calendar.time
        _selectedEndAt.value = endDate(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                _dayOfEnd.value!!
        )

        dateLittleText.value = week.toString()
    }

    private fun getDontDate() {
        val today = Date()
        val calendar = Calendar.getInstance()
        calendar.time = today

        _selectedStartAt.value = startDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        _selectedEndAt.value = endDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            _dayOfEnd.value!!
        )

        val formatter = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA)
        dateDontText.value = formatter.format(today)
    }
}