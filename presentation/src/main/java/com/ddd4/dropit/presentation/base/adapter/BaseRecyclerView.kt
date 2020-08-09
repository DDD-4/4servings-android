package com.ddd4.dropit.presentation.base.adapter

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView {

    abstract class Adapter<B: ViewDataBinding>(
        @LayoutRes private val layoutId: Int,
        private var items: List<Any>?,
        private val bindingVariableId: Int? = null
    ) : RecyclerView.Adapter<BaseViewHolder<B>>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            object : BaseViewHolder<B>(layoutId, parent, bindingVariableId) {}

        override fun getItemCount() = if (items != null) items!!.size else 0

        override fun onBindViewHolder(holder: BaseViewHolder<B>, position: Int) {
            holder.onBind(items?.get(position))
        }
    }
}