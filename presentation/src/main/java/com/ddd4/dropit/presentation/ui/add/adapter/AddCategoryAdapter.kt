package com.ddd4.dropit.presentation.ui.add.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.BR
import com.ddd4.dropit.presentation.databinding.RowAddCategoryBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.util.ItemClickListener

class AddCategoryAdapter(
    var items: List<PresentationEntity.Category>?,
    val clickListener: ItemClickListener
): RecyclerView.Adapter<AddCategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = RowAddCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int = if (items != null) items!!.size else 0

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items!![position])
    }

    inner class CategoryViewHolder constructor(
        val binding: RowAddCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: PresentationEntity.Category) {
            binding.setVariable(BR.item, item)
            binding.isSelected = false
            itemView.setOnClickListener {
                if (selectedPosition != adapterPosition) {
                    binding.isSelected = true
                    notifyItemChanged(selectedPosition)
                    selectedPosition = adapterPosition
                    clickListener.onItemClicked(item)
                }
            }
        }
    }
}