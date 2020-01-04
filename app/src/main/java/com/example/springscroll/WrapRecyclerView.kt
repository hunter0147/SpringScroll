package com.example.springscroll

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class WrapRecyclerView : RecyclerView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, 0)
    }
}
