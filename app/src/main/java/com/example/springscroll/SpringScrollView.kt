package com.example.springscroll

import android.content.Context
import android.util.AttributeSet
import android.widget.OverScroller
import android.widget.ScrollView

import java.lang.reflect.Field

class SpringScrollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {
    val MAX_OVER_SCROLL_Y = 300

    private val scrollRange: Int
        get() = if (childCount > 0) {
            Math.max(0, getChildAt(0).height - (height - paddingBottom - paddingTop))
        } else 0

    override fun canScrollVertically(direction: Int): Boolean {
        return true
    }

    override fun dispatchNestedFling(
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        if (!consumed) {
            try {
                val scrollview = ScrollView::class.java
                val scrollField = scrollview.getDeclaredField("mScroller")
                scrollField.isAccessible = true
                val scroller = scrollField.get(this) as OverScroller
                if (scroller.springBack(
                        scrollX, scrollY, 0, 0, 0,
                        scrollRange
                    )
                ) {
                    postInvalidateOnAnimation()
                }
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        }
        return super.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun overScrollBy(
        deltaX: Int,
        deltaY: Int,
        scrollX: Int,
        scrollY: Int,
        scrollRangeX: Int,
        scrollRangeY: Int,
        maxOverScrollX: Int,
        maxOverScrollY: Int,
        isTouchEvent: Boolean
    ): Boolean {
        return super.overScrollBy(
            deltaX,
            deltaY,
            scrollX,
            scrollY,
            scrollRangeX,
            scrollRangeY,
            maxOverScrollX,
            MAX_OVER_SCROLL_Y,
            isTouchEvent
        )
    }
}
