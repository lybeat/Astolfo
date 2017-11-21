package com.arturia.astolfo.widget.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.arturia.astolfo.R;

/**
 * Author: sayaki
 * Date: 2016/12/9
 */
public class SwitchMenu extends FrameLayout implements IMenu {

    protected FrameLayout rootLayout;
    protected TextView titleTxt;
    protected TextView summaryTxt;
    protected View underline;
    protected SwitchCompat switchCompat;

    public SwitchMenu(Context context) {
        this(context, null);
    }

    public SwitchMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

        String title = "";
        String summary = "";
        boolean isChecked = false;
        boolean hasUnderline = false;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MenuItem, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.MenuItem_aliceTitle) {
                title = a.getString(attr);
            } else if (attr == R.styleable.MenuItem_aliceSummary) {
                summary = a.getString(attr);
            } else if (attr == R.styleable.MenuItem_aliceChecked) {
                isChecked = a.getBoolean(attr, false);
            } else if (attr == R.styleable.MenuItem_aliceUnderline) {
                hasUnderline = a.getBoolean(attr, false);
            }
        }
        a.recycle();

        if (!TextUtils.isEmpty(title)) {
            titleTxt.setText(title);
        }
        if (!TextUtils.isEmpty(summary)) {
            summaryTxt.setText(summary);
        } else {
            summaryTxt.setVisibility(GONE);
        }
        switchCompat.setChecked(isChecked);
        underline.setVisibility(hasUnderline ? VISIBLE : GONE);
    }

    private void init() {
        inflate(getContext(), R.layout.menu_switch, this);
        rootLayout = findViewById(R.id.root_layout);
        rootLayout.setBackgroundResource(R.drawable.bg_ripple);
        switchCompat = findViewById(R.id.switch_compat);
        titleTxt = findViewById(R.id.title_txt);
        summaryTxt = findViewById(R.id.summary_txt);
        underline = findViewById(R.id.underline);

        setEnabled(true);
        setClickable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                switchCompat.toggle();
                break;
        }

        return super.onTouchEvent(event);
    }

    public String getTitle() {
        return titleTxt.getText().toString();
    }

    public void setTitle(String title) {
        titleTxt.setText(title);
    }

    public String getSummary() {
        return summaryTxt.getText().toString();
    }

    public void setSummary(String summary) {
        summaryTxt.setText(summary);
    }

    public void setChecked(boolean checked) {
        switchCompat.setChecked(checked);
    }

    public boolean isChecked() {
        return switchCompat.isChecked();
    }

    @Override
    public void setTitleColor(int titleColor) {
        titleTxt.setTextColor(titleColor);
    }

    @Override
    public void setSummaryColor(int summaryColor) {
        summaryTxt.setTextColor(summaryColor);
    }

    @Override
    public void setUnderlineResource(int resid) {
        underline.setBackgroundResource(resid);
    }

    @Override
    public void setRootLayoutResource(int resId) {
        rootLayout.setBackgroundResource(resId);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
