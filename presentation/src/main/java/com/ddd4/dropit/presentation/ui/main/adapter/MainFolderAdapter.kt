package com.ddd4.dropit.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.BR
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.databinding.RowHomeFolderBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.util.ItemClickListener

class MainFolderAdapter(
    var items: List<PresentationEntity.Folder>?,
    var clickListener: ItemClickListener
): RecyclerView.Adapter<MainFolderAdapter.FolderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        return FolderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_home_folder, parent, false))
    }

    override fun getItemCount(): Int = if (items != null) items!!.size else 0

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.binding.item = items!![position]

        holder.itemView.setOnClickListener {
            clickListener.onItemClicked(items!![position])
        }
    }

    class FolderViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding: RowHomeFolderBinding = DataBindingUtil.bind(view)!!
    }
}
