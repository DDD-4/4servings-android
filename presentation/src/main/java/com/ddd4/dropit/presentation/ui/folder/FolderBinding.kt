package com.ddd4.dropit.presentation.ui.folder

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.BR
import com.ddd4.dropit.presentation.base.adapter.BaseRecyclerView
import com.ddd4.dropit.presentation.databinding.RowDetailItemFolderBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.util.loadUrl
import com.ddd4.dropit.presentation.util.loadUrlCenterCrop

object FolderBinding {

    @JvmStatic
    @BindingAdapter(value = ["folderItem"])
    fun setDetailFolderItems(view: RecyclerView, items: List<PresentationEntity.Folder>?){
        view.adapter = object : BaseRecyclerView.Adapter<RowDetailItemFolderBinding>(
            R.layout.row_detail_item_folder, items, BR.detailFolderItem) {}
    }

    @JvmStatic
    @BindingAdapter(value = ["setImage"])
    fun setImageView(view: ImageView, src: String){
        view.loadUrlCenterCrop(src)
    }
}