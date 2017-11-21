package com.arturia.astolfo.widget.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.arturia.astolfo.R;

/**
 * Author: sayaki
 * Date: 2016/12/9
 */
public class StateMenu extends FrameLayout implements IMenu {

    protected FrameLayout rootLayout;
    protected TextView titleTxt;
    protected TextView summaryTxt;
    protected View underline;
    protected TextView stateTxt;
    protected ImageView iconImg;

    public StateMenu(Context context) {
        this(context, null);
    }

    public StateMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

        String title = "";
        String summary = "";
        String state = "";
        boolean hasUnderline = false;
        Drawable icon = null;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MenuItem, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.MenuItem_aliceTitle) {
                title = a.getString(attr);
            } else if (attr == R.styleable.MenuItem_aliceSummary) {
                summary = a.getString(attr);
            } else if (attr == R.styleable.MenuItem_aliceState) {
                state = a.getString(attr);
            } else if (attr == R.styleable.MenuItem_aliceUnderline) {
                hasUnderline = a.getBoolean(attr, false);
            } else if (attr == R.styleable.MenuItem_aliceIcon) {
                icon = a.getDrawable(attr);
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
        if (!TextUtils.isEmpty(state)) {
            stateTxt.setText(state);
        }
        underline.setVisibility(hasUnderline ? VISIBLE : GONE);
        if (icon != null) {
            iconImg.setImageDrawable(icon);
        }
    }

    private void init() {
        inflate(getContext(), R.layout.menu_state, this);
        rootLayout = findViewById(R.id.root_layout);
        rootLayout.setBackgroundResource(R.drawable.bg_ripple);
        stateTxt = findViewById(R.id.state_txt);
        titleTxt = findViewById(R.id.title_txt);
        summaryTxt = findViewById(R.id.summary_txt);
        underline = findViewById(R.id.underline);
        iconImg = findViewById(R.id.icon_img);

        setEnabled(true);
        setClickable(true);
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

    public String getState() {
        return stateTxt.getText().toString();
    }

    public void setState(String state) {
        stateTxt.setText(state);
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
