package com.ddd4.dropit.presentation.ui.add.screen

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseFragment
import com.ddd4.dropit.presentation.databinding.FragmentAddContentsBinding
import com.ddd4.dropit.presentation.ui.add.AddSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddContentsFragment : BaseFragment<FragmentAddContentsBinding>(R.layout.fragment_add_contents) {

    private val addSharedViewModel: AddSharedViewModel by activityViewModels()

    override fun setBind() {
        binding.apply {
            addVM = addSharedViewModel
        }
    }

    override fun setObserve() {
        addSharedViewModel.nextClick.observe(this, Observer {
            findNavController().navigate(AddContentsFragmentDirections.actionAddContentsFragmentToAddDateFragment())
        })
        addSharedViewModel.captureClick.observe(this, Observer {
            //카메라 캡쳐 이동
        })
    }

    override fun onResume() {
        super.onResume()

        addSharedViewModel.setProgressValue(66)
    }
}