package com.ddd4.dropit.presentation.ui.folder

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.BR
import com.ddd4.dropit.presentation.databinding.RowDetailFolderBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope

class FolderAdapter(
    private val onItemClick: ItemHandler? = null
) :
    ListAdapter<Item, FolderAdapter.FolderViewHolder>(FolderDiffCallback()) {

    private var selectedView = SparseBooleanArray(itemCount)
    private var selectedImageState = false

    fun setImageState(state: Boolean?) {
         state?.let {
             selectedImageState = it
        }
    }

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

        fun bind(item: Item, position: Int) {
            for(i in 0 until itemCount){
                selectedView.put(i, false)
            }

            binding.setVariable(BR.item, item)
            binding.viewShadow.visibility = View.GONE

            binding.folderLayout.setOnClickListener {
                if(selectedImageState) { //다중선택시
                    selectedView.put(position, !selectedView.get(position))
                        if (selectedView.get(position)) {
                            binding.viewShadow.visibility = View.VISIBLE
                        } else {
                            binding.viewShadow.visibility = View.GONE
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
    class FolderDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }