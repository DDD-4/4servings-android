package com.ddd4.dropit.presentation.ui.add.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseFragment
import com.ddd4.dropit.presentation.databinding.FragmentAddSectionBinding
import com.ddd4.dropit.presentation.ui.add.AddSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSectionFragment : BaseFragment<FragmentAddSectionBinding>(R.layout.fragment_add_section) {

    private val addSharedViewModel: AddSharedViewModel by activityViewModels()

    override fun setBind() {
        binding.apply {
            addVM = addSharedViewModel
        }
    }

    override fun setObserve() {
        addSharedViewModel.nextClick.observe(this, Observer {
            findNavController().navigate(AddSectionFragmentDirections.actionAddSectionFragmentToAddContentsFragment())
        })
    }

    override fun onResume() {
        super.onResume()

        addSharedViewModel.setProgressValue(33)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addSharedViewModel.getCategoryItems()
    }
}