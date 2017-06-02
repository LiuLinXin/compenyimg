package com.hc.renderscript;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by 39716 on 2017/5/1.
 */

public class ButtonVie3w extends Button {
    public ButtonVie3w(Context context) {
        super(context);
    }

    public ButtonVie3w(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonVie3w(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("hx", "buview onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("hx", "buview dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }
}
