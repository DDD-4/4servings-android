package com.ddd4.dropit.presentation.ui.add

import android.app.DatePickerDialog
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd4.dropit.presentation.entity.PresentationEntity
import com.ddd4.dropit.presentation.ui.add.adapter.AddCategoryAdapter
import com.ddd4.dropit.presentation.ui.add.adapter.AddSubCategoryAdapter
import com.ddd4.dropit.presentation.util.ItemClickListener
import com.ddd4.dropit.presentation.util.loadUrl
import java.util.*


object AddBinding {

    @JvmStatic
    @BindingAdapter(value = ["addItems", "clickListener"])
    fun setAddCategoryItems(view: RecyclerView, addItems: List<PresentationEntity.Category>?, clickListener: ItemClickListener) {
        view.adapter?.run {
            if (this is AddCategoryAdapter) {
                this.items = addItems
                this.notifyDataSetChanged()
            }
        } ?: run {
            AddCategoryAdapter(addItems, clickListener).apply {
                view.adapter = this
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["addItems", "clickListener"])
    fun setSubCategoryItems(view: RecyclerView, addItems: List<PresentationEntity.SubCategory>?, clickListener: ItemClickListener) {
        view.adapter = AddSubCategoryAdapter(addItems, clickListener)
    }

    @JvmStatic
    @BindingAdapter(value = ["textChangeWatcher"])
    fun setNameTextWatcher(view: EditText, textWatcher: TextWatcher) {
        view.addTextChangedListener(textWatcher)
    }

    @JvmStatic
    @BindingAdapter(value = ["datePickerListener"])
    fun setDatePickerListener(view: TextView, listener: DatePickerDialog.OnDateSetListener) {
        view.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(view.context, listener, year, month, day)
            dialog.show()
        }
    }

    @JvmStatic
    @BindingAdapter("image")
    fun setItemImage(view: ImageView, image: String?) {
        view.loadUrl(image)
        view.scaleType = ImageView.ScaleType.CENTER_CROP
    }
}