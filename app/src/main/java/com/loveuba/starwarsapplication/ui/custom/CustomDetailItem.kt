package com.loveuba.starwarsapplication.ui.custom

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.loveuba.starwarsapplication.R
import kotlinx.android.synthetic.main.layout_custom_detail_item.view.*

class CustomDetailItem @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    init {
        inflate(context, R.layout.layout_custom_detail_item, this)
        val ta: TypedArray =
            context.obtainStyledAttributes(attributeSet, R.styleable.CustomDetailView)
        detailTitleTv.text = ta.getString(R.styleable.CustomDetailView_title)
        mainDetailTv.text = ta.getString(R.styleable.CustomDetailView_subtitle)

        ta.recycle()
    }

    fun setTitleText(text : String){
        detailTitleTv.text = text
    }
    fun setDetailText(text : String){
        mainDetailTv.text = text
    }
}