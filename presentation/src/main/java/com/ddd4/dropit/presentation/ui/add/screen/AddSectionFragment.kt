package com.ddd4.dropit.presentation.ui.add.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseFragment
import com.ddd4.dropit.presentation.databinding.FragmentAddSectionBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.ui.add.AddSharedViewModel
import com.ddd4.dropit.presentation.util.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSectionFragment : BaseFragment<FragmentAddSectionBinding>(R.layout.fragment_add_section) {

    private val addSharedViewModel: AddSharedViewModel by activityViewModels()

    override fun setBind() {
        binding.apply {
            view = this@AddSectionFragment
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

    val onItemClickListener = object: ItemClickListener {
        override fun <T> onItemClicked(item: T) {
            when (item) {
                is PresentationEntity.Category -> {
                    addSharedViewModel.onCategoryClicked(item.id)
                }
                is PresentationEntity.SubCategory -> {
                    addSharedViewModel.onSubCategoryClicked(item.id, item.endAt)
                }
            }
        }
    }
}