package com.arturia.astolfo.widget.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arturia.astolfo.R;
import com.arturia.astolfo.util.ColorUtil;

/**
 * Author: sayaki
 * Date: 2016/12/9
 */
public class CategoryMenu extends FrameLayout {

    private LinearLayout rootLayout;
    private TextView categoryTxt;

    public CategoryMenu(Context context) {
        this(context, null);
    }

    public CategoryMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CategoryMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CategoryMenu, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.CategoryMenu_aliceCategory) {
                String category = a.getString(attr);
                if (!TextUtils.isEmpty(category)) {
                    categoryTxt.setText(category);
                }
            }
        }
        a.recycle();
    }

    public void init() {
        inflate(getContext(), R.layout.menu_category, this);
        rootLayout = findViewById(R.id.root_layout);
        rootLayout.setBackgroundColor(ColorUtil.getColor(getContext(), R.color.white));
        categoryTxt = findViewById(R.id.category_txt);

        setEnabled(true);
        setClickable(true);
    }

    public void setCategory(String title) {
        categoryTxt.setText(title);
    }

    public String getCategory() {
        return categoryTxt.getText().toString();
    }

    public void setCategoryColor(int categoryColor) {
        categoryTxt.setTextColor(categoryColor);
    }

    public void setRootLayoutResource(int resId) {
        rootLayout.setBackgroundResource(resId);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
