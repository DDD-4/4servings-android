package com.ddd4.dropit.presentation.ui.folder

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.BR
import com.ddd4.dropit.presentation.base.adapter.BaseRecyclerView
import com.ddd4.dropit.presentation.databinding.RowDetailFolderBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.util.loadUrlCenterCrop
import timber.log.Timber

object FolderBinding {

    @JvmStatic
    @BindingAdapter(value = ["folderItem"])
    fun setDetailFolderItems(view: RecyclerView, items: List<PresentationEntity.Folder>?){
        view.adapter = object : BaseRecyclerView.Adapter<RowDetailFolderBinding>(
            layoutId = R.layout.row_detail_folder, items = items, bindingVariableId = BR.detailFolderItem
        ) {}
    }

    @JvmStatic
    @BindingAdapter(value = ["setImage"])
    fun setImageView(view: ImageView, src: String){
        view.loadUrlCenterCrop(src)
    }
}