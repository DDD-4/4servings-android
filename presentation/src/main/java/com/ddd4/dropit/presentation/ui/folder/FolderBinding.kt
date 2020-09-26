package com.ddd4.dropit.presentation.ui.folder

import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.ui.category.CategoryAdapter
import com.ddd4.dropit.presentation.ui.moveFolder.MoveFolderAdapter
import com.ddd4.dropit.presentation.util.ItemClickListener
import com.ddd4.dropit.presentation.util.loadUrlCenterCrop
import timber.log.Timber


@BindingAdapter("folderItem", "listener", "imageState")
fun setFolderItemRecyclerView(
    recyclerView: RecyclerView,
    list: List<PresentationEntity.Item>?,
    listener: ItemHandler,
    imageState: Boolean
) {
    list?.let {
        Timber.e("first binding end! size: ${it.size}")
        recyclerView.apply {
            adapter = FolderAdapter(listener)
        }

        (recyclerView.adapter as FolderAdapter).run {
            submitList(it)
            setImageState(imageState)
        }

        recyclerView.adapter?.notifyDataSetChanged()
    }
}

@BindingAdapter("categoryItem", "listener", "imageState")
fun setCategoryItemRecyclerView(
    recyclerView: RecyclerView,
    list: List<PresentationEntity.Item>?,
    listener: ItemHandler,
    imageState: Boolean
    ) {
    list?.let {
        recyclerView.apply {
            adapter = CategoryAdapter(listener)
        }

        (recyclerView.adapter as CategoryAdapter).run {
            submitList(it)
            setImageState(imageState)
        }

        Timber.e("first binding end! size: ${it.size}")
        recyclerView.adapter?.notifyDataSetChanged()
    }
}

@BindingAdapter("setImage")
fun setImageUrl(view: ImageView, src: String?) {
    src?.let{
        view.loadUrlCenterCrop(src)
        view.clipToOutline = true
    }
}

@BindingAdapter("setItems", "setListener")
fun setFolderRecyclerView(recyclerView: RecyclerView, list: List<PresentationEntity.Folder>?, listener: ItemClickListener) {
    list?.let {
        recyclerView.adapter = MoveFolderAdapter(listener)
        (recyclerView.adapter as MoveFolderAdapter).submitList(it)
    }
}