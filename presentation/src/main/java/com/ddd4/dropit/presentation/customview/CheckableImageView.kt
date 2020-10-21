package com.ddd4.dropit.presentation.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.ui.folder.setFolderImageUrl
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.chekable_image_view.view.*
import kotlinx.android.synthetic.main.row_detail_folder.view.*

/**
 * 필요한 요소 : check image를 넣을 layout
 *              -> 체크 이미지를 수정 가능으로 할지, 혹은 디폴트로 넣고 수정 불가능으로 할지 결정해야 합니다.
 *              이미지를 넣을 card view or image view,
 *              체크 여부를 알 수 있는 attribute
 *              -> 이를 위해 해당 entity 에 체크 되었는지 대한 여부를 추가해야 합니다.
 *
 */
class CheckableImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val checkImage: FrameLayout
    private val itemImageView: ImageView

    private var dDayTextView: TextView
    private var dday: String? = null

    private var isIvChecked = false

    init {
        val arr = context.obtainStyledAttributes(
            attrs, R.styleable.CheckableImageView, defStyleAttr, R.style.dropit_cardview
        )
        dday = arr.getString(R.styleable.CheckableImageView_dayText)

        arr.recycle()

        LayoutInflater.from(context).inflate(R.layout.chekable_image_view, this, true)

        itemImageView = findViewById(R.id.ivDetailFolder)
        checkImage = findViewById(R.id.checkView)
        dDayTextView = findViewById(R.id.tvDetailFolder)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(widthSize, widthSize)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun setChecked(checked: Boolean) {
        super.setChecked(checked)
        isIvChecked = checked

        when(checked) {
            true -> setIvChecked()
            false -> setIvDefault()
        }
    }

    override fun isChecked(): Boolean = isIvChecked

    fun setImageUrl(imageUrl: String?) {
        imageUrl?.let {
            setFolderImageUrl(itemImageView, imageUrl)
        }
    }

    fun setIsSelectMode(isSelectMode: Boolean) {
        if(isSelectMode) {
            checkImage.visibility = View.VISIBLE
        } else {
            checkImage.visibility = setCheckImageVisibility(isIvChecked)
        }
    }

    private fun setCheckImageVisibility(checked: Boolean): Int {
       return if(checked) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun setItemClick(l: OnClickListener?){
        checkImage.setOnClickListener(l)
    }

    fun setDayText(date: String?) {
        date?.let {
            dday = it
            if(it == "D-Day") {
                dDayTextView.background = resources.getDrawable(R.drawable.bg_dday_blue_border, null)
            }
            dDayTextView.text = dday
        }
    }

    private fun setIvChecked() {
        context?.let {
            checkView.visibility = View.VISIBLE
        }
    }

    private fun setIvDefault() {
        context?.let {
            checkView.visibility = View.GONE
        }
    }

}