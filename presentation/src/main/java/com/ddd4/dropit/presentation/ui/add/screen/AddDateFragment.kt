package com.ddd4.dropit.presentation.ui.add.screen

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseFragment
import com.ddd4.dropit.presentation.databinding.FragmentAddDateBinding
import com.ddd4.dropit.presentation.ui.add.AddSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddDateFragment : BaseFragment<FragmentAddDateBinding>(R.layout.fragment_add_date) {

    private val addSharedViewModel: AddSharedViewModel by activityViewModels()

    override fun setBind() {
        binding.apply {
            addVM = addSharedViewModel
        }
    }

    override fun setObserve() {
        addSharedViewModel.nextClick.observe(this, Observer {
            addSharedViewModel.setItem()
        })
        addSharedViewModel.addComplete.observe(this, Observer {
            requireActivity().finish()
        })
    }

    override fun onResume() {
        super.onResume()

        addSharedViewModel.setProgressValue(100)
    }
}