package com.ddd4.dropit.presentation.ui.add.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.databinding.RowAddCategoryBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.util.ItemClickListener

class AddCategoryAdapter(
    var items: List<PresentationEntity.Category>?,
    val clickListener: ItemClickListener
): RecyclerView.Adapter<AddCategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_add_category, parent, false))
    }

    override fun getItemCount(): Int = if (items != null) items!!.size else 0

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.item = items!![position]
        holder.binding.isSelected = false

        holder.itemView.setOnClickListener {
            if (selectedPosition != position) {
                notifyItemChanged(selectedPosition)
                holder.binding.isSelected = true
                selectedPosition = position
                clickListener.onItemClicked(items!![position])
            }
        }
    }

    class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding: RowAddCategoryBinding = DataBindingUtil.bind(view)!!
    }
}