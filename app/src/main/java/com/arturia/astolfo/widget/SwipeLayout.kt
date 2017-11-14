package com.arturia.astolfo.widget

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.arturia.astolfo.R






/**
 * Author: Arturia
 * Date: 2017/11/14
 */
open class SwipeLayout : FrameLayout {

    private var activity: Activity? = null
    private var shadowDrawable: Drawable? = null
    private var shadowWidth: Int = 0
    private var moveLeft: Int = 0
    private var isEdge: Boolean = false
    private var viewDragHelper: ViewDragHelper? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        shadowWidth = dp2px(context, 10.0f)
        shadowDrawable = resources.getDrawable(R.drawable.shadow_left)

        viewDragHelper = ViewDragHelper.create(this, 1.0f, object : ViewDragHelper.Callback() {
            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                if (isEdge) {
                    isEdge = false
                    return true
                }
                return false
            }

            override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
                moveLeft = left
                if (left >= width) {
                    activity?.finish()
                }
                invalidate()
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int =
                    Math.min(width, Math.max(left, 0))

            override fun getViewHorizontalDragRange(child: View): Int = child.width

            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                if (moveLeft > width * 0.5 || xvel > 4000) {
                    viewDragHelper?.settleCapturedViewAt(width, top)
                } else {
                    viewDragHelper?.settleCapturedViewAt(0, top)
                }
                invalidate()
            }

            override fun onEdgeDragStarted(edgeFlags: Int, pointerId: Int) {
                isEdge = true
            }
        })
        viewDragHelper?.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT)
    }

    override fun computeScroll() {
        if (viewDragHelper?.continueSettling(true)!!) {
            invalidate()
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean = viewDragHelper?.shouldInterceptTouchEvent(ev)!!

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        viewDragHelper?.processTouchEvent(event)
        return true
    }

    protected override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        drawShadow(canvas)
    }

    private fun drawShadow(canvas: Canvas) {
        shadowDrawable?.setBounds(0, 0, shadowWidth, height)
        canvas.save()
        canvas.translate((moveLeft - shadowWidth).toFloat(), 0f)
        shadowDrawable?.draw(canvas)
        canvas.restore()
    }

    fun bindActivity(activity: Activity) {
        this.activity = activity
        val decorView = activity.window.decorView as ViewGroup
        val child = decorView.getChildAt(0)
        decorView.removeView(child)
        this.addView(child)
        decorView.addView(this)
    }

    private fun dp2px(context: Context, dpVal: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.resources.displayMetrics).toInt()
    }
}