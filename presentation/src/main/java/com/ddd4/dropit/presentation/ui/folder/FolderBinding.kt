package com.ddd4.dropit.presentation.ui.folder

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.ui.category.CategoryAdapter
import com.ddd4.dropit.presentation.ui.moveFolder.MoveFolderAdapter
import com.ddd4.dropit.presentation.util.ItemClickListener
import com.ddd4.dropit.presentation.util.loadUrlCenterCrop
import timber.log.Timber


@BindingAdapter("folderItem")
fun setFolderItemRecyclerView(recyclerView: RecyclerView, list: List<PresentationEntity.Item>?) {
    list?.let {
        (recyclerView.adapter as FolderAdapter).submitList(it)
        Timber.e("first binding end! size: ${it.size}")
        recyclerView.adapter?.notifyDataSetChanged()
    }
}

@BindingAdapter("categoryItem")
fun setCategoryItemRecyclerView(recyclerView: RecyclerView, list: List<PresentationEntity.Item>?) {
    list?.let {
        (recyclerView.adapter as CategoryAdapter).submitList(it)
        Timber.e("first binding end! size: ${it.size}")
        recyclerView.adapter?.notifyDataSetChanged()
    }
}


@BindingAdapter("setImage")
fun setImageUrl(view: ImageView, src: String) {
    view.loadUrlCenterCrop(src)
}

@BindingAdapter("setItems", "setListener")
fun setFolderRecyclerView(recyclerView: RecyclerView, list: List<PresentationEntity.Folder>?, listener: ItemClickListener) {
    list?.let {
        recyclerView.adapter = MoveFolderAdapter(listener)
        (recyclerView.adapter as MoveFolderAdapter).submitList(it)
    }
}