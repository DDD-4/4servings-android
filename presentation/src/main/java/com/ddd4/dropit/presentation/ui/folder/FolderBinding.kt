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
<<<<<<< HEAD
<<<<<<< HEAD
import com.ddd4.dropit.presentation.util.loadDrawable
import com.ddd4.dropit.presentation.util.loadResource
=======
>>>>>>> Add 2-1-2 folder selection layout
=======
import com.ddd4.dropit.presentation.util.loadDrawable
import com.ddd4.dropit.presentation.util.loadResource
>>>>>>> Fix FolderAdapter due to dependence of view data
import com.ddd4.dropit.presentation.util.loadUrlCenterCrop
import timber.log.Timber


<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Fix FolderAdapter due to dependence of view data
@BindingAdapter("folderItem")
fun setRecyclerView(recyclerView: RecyclerView, list: List<PresentationEntity.Item>?) {
    list?.let {
        (recyclerView.adapter as FolderAdapter).submitList(it)
        Timber.e("first binding end! size: ${it.size}")
        recyclerView.adapter?.notifyDataSetChanged()
<<<<<<< HEAD
=======
    @JvmStatic
    @BindingAdapter(value = ["folderItem"])
    fun setDetailFolderItems(view: RecyclerView, items: List<PresentationEntity.Folder>?){
        view.adapter = object : BaseRecyclerView.Adapter<RowDetailFolderBinding>(
            layoutId = R.layout.row_detail_folder, items = items, bindingVariableId = BR.detailFolderItem
        ) {}
>>>>>>> Add 2-1-2 folder selection layout
=======
>>>>>>> Fix FolderAdapter due to dependence of view data
    }
}

@BindingAdapter("setImage")
fun setImageUrl(view: ImageView, src: String) {
    view.loadUrlCenterCrop(src)
}