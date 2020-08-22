package com.ddd4.dropit.presentation.ui.folder

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.databinding.RowDetailFolderBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity.*


class FolderAdapter(
    private val onItemClick: ItemHandler?= null
) :
    ListAdapter<Folder, FolderAdapter.FolderViewHolder>(FolderDiffCallback()) {

    private var selectedView = SparseBooleanArray(itemCount)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowDetailFolderBinding.inflate(layoutInflater, parent, false)

        return FolderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }


    inner class FolderViewHolder constructor(val binding: RowDetailFolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Folder, position: Int) {
            for(i in 0 until itemCount){
                selectedView.put(i, false)
            }
            binding.folder = item
            binding.viewShadow.visibility = View.GONE

            binding.folderLayout.setOnClickListener {
                selectedView.put(position, !selectedView.get(position))
                binding.viewShadow.visibility =
                    if(selectedView.get(position)) { View.VISIBLE }
                    else { View.GONE }
                onItemClick?.onItemClicked(item, selectedView.get(position))

            }
        }
    }
}

    /**
     * Callback for calculating the diff between two non-null items in a list.
     *
     * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
     * list that's been passed to `submitList`.
     */
    class FolderDiffCallback : DiffUtil.ItemCallback<Folder>() {
        override fun areItemsTheSame(oldItem: Folder, newItem: Folder): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Folder, newItem: Folder): Boolean {
            return oldItem == newItem
        }
    }