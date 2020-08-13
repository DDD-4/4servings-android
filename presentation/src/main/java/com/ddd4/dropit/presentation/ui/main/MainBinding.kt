package com.ddd4.dropit.presentation.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.BR
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.adapter.BaseRecyclerView
import com.ddd4.dropit.presentation.databinding.RowHomeFolderBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity

object MainBinding {

    @JvmStatic
    @BindingAdapter(value = ["items"])
    fun setFolderItems(view: RecyclerView, items: List<PresentationEntity.Folder>?) {
        view.adapter = object : BaseRecyclerView.Adapter<RowHomeFolderBinding>(
            R.layout.row_home_folder, items, BR.item) {}
    }
}