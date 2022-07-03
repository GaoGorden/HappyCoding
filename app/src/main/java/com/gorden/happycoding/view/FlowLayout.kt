package com.gorden.happycoding.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import kotlin.math.max

class FlowLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ViewGroup(context, attrs, defStyleAttr) {

    companion object {
        const val TAG = "FlowLayout"
    }

    // 测量，布局

    // 用于计算本View的宽高
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = 0
        var height = 0

        var parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        var parentHeight = MeasureSpec.getSize(heightMeasureSpec)

        var widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var heightMode = MeasureSpec.getMode(heightMeasureSpec)

        Log.e(
            TAG, "onMeasure: parentMode:${
                when (widthMode) {
                    MeasureSpec.AT_MOST -> {
                        "wrap_parent"
                    }
                    else -> {
                        "most_parent || dp"
                    }
                }
            }"
        )

        var totalWidth = 0
        var totalHeight = 0

        var columnWidth = 0
        var columnHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(measuredWidth, measuredHeight)
            val rest = parentWidth - columnWidth
            if (rest > child.measuredWidth) {
                columnWidth += child.measuredWidth
                columnHeight = max(columnHeight, child.measuredHeight)
            } else {
                totalWidth = max(totalWidth, columnWidth)
                totalHeight += columnHeight
            }
        }

        // 处理一行还是多行
        if (totalWidth == 0) {
            totalWidth = columnWidth
            totalHeight = columnHeight
        } else {
            totalHeight += columnHeight
        }


        if (widthMode == MeasureSpec.EXACTLY) {
            totalWidth = parentWidth
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            totalHeight = parentHeight
        }

//        children.forEach {
//            if (columnWidth < parentWidth) {
//                columnWidth += it.measuredWidth
//                columnHeight = max(columnHeight, it.measuredHeight)
//            } else {
//                totalWidth = max(totalWidth, columnWidth)
//                totalHeight += it.measuredHeight
//            }
//        }

        width = totalWidth
        height = totalHeight

        Log.e(TAG, "onMeasure: parentWidth = $parentWidth")
        Log.e(TAG, "onMeasure: width = $width")
        Log.e(TAG, "onMeasure: height = $height")

        setMeasuredDimension(width, height)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val left = l
        val top = t
        val right = r
        val bottom = b

        val child = getChildAt(0)
        child.layout(l, t, l + child.measuredWidth, t + child.measuredHeight)
        children.forEach {
//            it.layout(l,t,0,0)
        }
    }

}