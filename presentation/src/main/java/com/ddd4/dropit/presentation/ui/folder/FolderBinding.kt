package com.ddd4.dropit.presentation.ui.folder

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.BR
import com.ddd4.dropit.presentation.base.adapter.BaseRecyclerView
import com.ddd4.dropit.presentation.databinding.RowDetailFolderBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity

import com.ddd4.dropit.presentation.ui.category.CategoryAdapter
import com.ddd4.dropit.presentation.ui.moveFolder.MoveFolderAdapter
import com.ddd4.dropit.presentation.util.ItemClickListener
import com.ddd4.dropit.presentation.util.loadDrawable
import com.ddd4.dropit.presentation.util.loadResource
import com.ddd4.dropit.presentation.util.loadUrlCenterCrop
import timber.log.Timber


@BindingAdapter("folderItem")
fun setFolderItemRecyclerView(recyclerView: RecyclerView, list: List<PresentationEntity.Item>?) {
    list?.let {
        (recyclerView.adapter as FolderAdapter).submitList(it)
        Timber.e("first binding end! size: ${it.size}")
        recyclerView.adapter?.notifyDataSetChanged()

    @BindingAdapter(value = ["folderItem"])
    fun setDetailFolderItems(view: RecyclerView, items: List<PresentationEntity.Folder>?){
        view.adapter = object : BaseRecyclerView.Adapter<RowDetailFolderBinding>(
            layoutId = R.layout.row_detail_folder, items = items, bindingVariableId = BR.detailFolderItem
        ) {}
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