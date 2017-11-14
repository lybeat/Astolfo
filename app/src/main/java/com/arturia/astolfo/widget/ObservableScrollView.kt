package com.arturia.astolfo.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView

/**
 * Author: Arturia
 * Date: 2017/11/14
 */
class ObservableScrollView : ScrollView {

    private lateinit var listener: ScrollViewListener

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    interface ScrollViewListener {
        fun onScrollChanged(view: ObservableScrollView, x: Int, y: Int, oldX: Int, oldY: Int)
    }

    fun setOnScrollChangedListener(listener: ScrollViewListener) {
        this.listener = listener
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        listener.onScrollChanged(this, l, t, oldl, oldt)
    }
}