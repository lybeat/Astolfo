package com.arturia.astolfo.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Author: sayaki
 * Date: 2016/3/21
 */
public class FlowImageView extends AppCompatImageView {

    private int originalWidth;

    private int originalHeight;

    public FlowImageView(Context context) {
        super(context);
    }

    public FlowImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOriginalSize(int originalWidth, int originalHeight) {
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (originalWidth > 0 && originalHeight > 0) {
            float ratio = (float) originalHeight / originalWidth;
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width * ratio);
            setMeasuredDimension(width, height);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        setImageBitmap(null);
//        setImageDrawable(null);
    }
}
