package com.ddd4.dropit.presentation.ui.category

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.databinding.RowDetailFolderBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity.*
import com.ddd4.dropit.presentation.ui.folder.FolderAdapter
import com.ddd4.dropit.presentation.ui.folder.FolderViewModel
import com.ddd4.dropit.presentation.ui.folder.ItemHandler

class CategoryAdapter(
    private val viewModel: CategoryViewModel,
    private val onItemClick: ItemHandler?= null
) :
    ListAdapter<Item, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    private var selectedView = SparseBooleanArray(itemCount)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowDetailFolderBinding.inflate(layoutInflater, parent, false)

        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position, onItemClick)
    }


    inner class CategoryViewHolder constructor(val binding: RowDetailFolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item, position: Int, listener: ItemHandler?) {
            for(i in 0 until itemCount){
                selectedView.put(i, false)
            }
            binding.item = item
            binding.viewShadow.visibility = View.GONE

            binding.folderLayout.setOnClickListener {
                if(viewModel.selectedImageState.value!!) { //다중선택시
                    selectedView.put(position, !selectedView.get(position))
                        if (selectedView.get(position)) {
                            binding.viewShadow.visibility = View.VISIBLE
                           // binding.tvDetailFolder.setTextColor(Color.WHITE)
                        } else {
                            binding.viewShadow.visibility = View.GONE
                           //
                            // binding.tvDetailFolder.setTextColor(Color.BLACK)
                        }
                    onItemClick?.onItemClicked(item, selectedView.get(position))
                }
                else {
                    onItemClick?.onItemDetailClicked(item)
                }
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
    class CategoryDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }