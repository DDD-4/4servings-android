package com.ddd4.dropit.presentation.ui.folder

<<<<<<< HEAD
import android.graphics.Color
=======
>>>>>>> Fix FolderAdapter due to dependence of view data
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
<<<<<<< HEAD
import com.ddd4.dropit.presentation.BR
import com.ddd4.dropit.presentation.databinding.RowDetailFolderBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity.*
import kotlinx.coroutines.selects.select


class FolderAdapter(
    private val viewModel: FolderViewModel,
=======
import com.ddd4.dropit.presentation.databinding.RowDetailFolderBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity.*


class FolderAdapter(
>>>>>>> Fix FolderAdapter due to dependence of view data
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
<<<<<<< HEAD
//            binding.setVariable(BR.selectedItem, selectedView)
//            binding.setVariable(BR.indexKey, position)
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
=======
            binding.viewShadow.visibility = View.GONE

            binding.folderLayout.setOnClickListener {
                selectedView.put(position, !selectedView.get(position))
                binding.viewShadow.visibility =
                    if(selectedView.get(position)) { View.VISIBLE }
                    else { View.GONE }
                onItemClick?.onItemClicked(item, selectedView.get(position))

>>>>>>> Fix FolderAdapter due to dependence of view data
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