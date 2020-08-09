package com.ddd4.dropit.presentation.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<B : ViewDataBinding>(
    @LayoutRes layoutId: Int,
    parent: ViewGroup,
    private val bindingVariableId: Int?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
) {
    val binding = DataBindingUtil.bind<B>(itemView)!!

    fun onBind(item: Any?) {
        try {
            bindingVariableId?.let {
                binding.setVariable(it, item)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}