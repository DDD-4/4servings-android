package com.ddd4.dropit.presentation.ui.main

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.ui.main.adapter.MainCategoryAdapter
import com.ddd4.dropit.presentation.ui.main.adapter.MainFolderAdapter
import com.ddd4.dropit.presentation.util.ItemClickListener

object MainBinding {

    @JvmStatic
    @BindingAdapter(value = ["mainItems", "clickListener"])
    fun setMainFolderItems(view: RecyclerView, mainItems: List<PresentationEntity.Folder>?, clickListener: ItemClickListener) {
        if (mainItems?.size ?: 0 < 2) {
            view.layoutManager =
                GridLayoutManager(view.context, 1, GridLayoutManager.HORIZONTAL, false)
        } else {
            view.layoutManager =
                GridLayoutManager(view.context, 2, GridLayoutManager.HORIZONTAL, false)
        }

        view.adapter?.run {
            if (this is MainFolderAdapter) {
                this.items = mainItems
                this.clickListener = clickListener
                this.notifyDataSetChanged()
            }
        } ?: run {
            MainFolderAdapter(mainItems, clickListener).apply {
                view.adapter = this
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["mainItems", "clickListener"])
    fun setMainCategoryItems(view: RecyclerView, mainItems: List<PresentationEntity.Category>?, clickListener: ItemClickListener) {
        view.adapter?.run {
            if (this is MainCategoryAdapter) {
                this.items = mainItems
                this.clickListener = clickListener
                this.notifyDataSetChanged()
            }
        } ?: run {
            MainCategoryAdapter(mainItems, clickListener).apply {
                view.adapter = this
            }
            val dividerItemDecoration = DividerItemDecoration(view.context, LinearLayoutManager.VERTICAL)
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(view.context, R.drawable.bg_divider)!!)
            view.addItemDecoration(dividerItemDecoration)
        }
    }
}
