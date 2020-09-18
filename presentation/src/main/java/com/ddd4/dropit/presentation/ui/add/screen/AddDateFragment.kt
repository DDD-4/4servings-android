package com.ddd4.dropit.presentation.ui.add.screen

import android.app.DatePickerDialog
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseFragment
import com.ddd4.dropit.presentation.databinding.FragmentAddDateBinding
import com.ddd4.dropit.presentation.ui.add.AddSharedViewModel
import com.ddd4.dropit.presentation.util.alarm.AlarmRegistUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_date.*
import javax.inject.Inject

@AndroidEntryPoint
class AddDateFragment : BaseFragment<FragmentAddDateBinding>(R.layout.fragment_add_date) {

    private val addSharedViewModel: AddSharedViewModel by activityViewModels()

    @Inject
    lateinit var alarmRegistUtil: AlarmRegistUtil

    override fun setBind() {
        binding.apply {
            view = this@AddDateFragment
            addVM = addSharedViewModel
        }
    }

    override fun setObserve() {
        addSharedViewModel.nextClick.observe(this, Observer {
            addSharedViewModel.setItem()
        })
        addSharedViewModel.addComplete.observe(this, Observer { alarmId ->
            alarmRegistUtil.setAlarm(alarmId)
            requireActivity().finish()
        })
    }

    override fun setInit() {
        rbtnLittleDate.setOnClickListener {
            addSharedViewModel.setLittleState()
        }
        rbtnDontDate.setOnClickListener {
            addSharedViewModel.setDontState()
        }
    }

    override fun onResume() {
        super.onResume()

        addSharedViewModel.setProgressValue(100)
    }

    val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        addSharedViewModel.setKnowDate(year, month, dayOfMonth)
    }

    val littleDateWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            addSharedViewModel.setLittleDate(s.toString())
        }
    }
}