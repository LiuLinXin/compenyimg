package com.hc.renderscript;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by 39716 on 2017/4/30.
 */

public class SonView extends RelativeLayout {
    public SonView(Context context) {
        super(context);
    }

    public SonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("hx", "son onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("hx", "son dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("hx", "son onTouchEvent");
        return super.onTouchEvent(event);
    }
}
