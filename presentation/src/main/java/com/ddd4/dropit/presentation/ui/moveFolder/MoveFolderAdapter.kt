package com.ddd4.dropit.presentation.ui.moveFolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.BR
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.databinding.RowAddCategoryBinding
import com.ddd4.dropit.presentation.databinding.RowDetailFolderBinding
import com.ddd4.dropit.presentation.databinding.RowDetailMoveFolderBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.util.ItemClickListener

class MoveFolderAdapter(
    private val clickListener: ItemClickListener? = null
): RecyclerView.Adapter<MoveFolderAdapter.MoveFolderViewHolder>() {

    private var items = mutableListOf<PresentationEntity.Folder>()

    fun submitList(item: List<PresentationEntity.Folder>){
        items.clear()
        items.addAll(item)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveFolderViewHolder {
        return MoveFolderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_detail_move_folder, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MoveFolderViewHolder, position: Int) {
        holder.binding.setVariable(BR.folderList, items[position])
        holder.itemView.setOnClickListener {
            clickListener?.onItemClicked(items[position])
        }
    }

    class MoveFolderViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding: RowDetailMoveFolderBinding = DataBindingUtil.bind(view)!!
    }
}