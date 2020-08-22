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
import com.ddd4.dropit.presentation.util.loadDrawable
import com.ddd4.dropit.presentation.util.loadResource
import com.ddd4.dropit.presentation.util.loadUrlCenterCrop
import timber.log.Timber


@BindingAdapter("folderItem")
fun setRecyclerView(recyclerView: RecyclerView, list: List<PresentationEntity.Folder>?) {
    list?.let {
        (recyclerView.adapter as FolderAdapter).submitList(it)
        Timber.e("first binding end! size: ${it.size}")
        recyclerView.adapter?.notifyDataSetChanged()
    }
}

@BindingAdapter("setImage")
fun setImageUrl(view: ImageView, src: String) {
    view.loadUrlCenterCrop(src)
}