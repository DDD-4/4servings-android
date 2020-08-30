package com.ddd4.dropit.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.databinding.RowHomeCategoryBinding
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.util.ItemClickListener

class MainCategoryAdapter(
    var items: List<PresentationEntity.Category>?,
    var clickListener: ItemClickListener
): RecyclerView.Adapter<MainCategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_home_category, parent, false))
    }

    override fun getItemCount(): Int = if (items != null) items!!.size else 0

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.item = items!![position]

        holder.itemView.setOnClickListener {
            clickListener.onItemClicked(items!![position])
        }
    }

    class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding: RowHomeCategoryBinding = DataBindingUtil.bind(view)!!
    }
}