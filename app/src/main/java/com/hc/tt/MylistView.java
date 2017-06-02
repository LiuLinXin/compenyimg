package com.hc.tt;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.a39716.tt.R;

/**
 * Created by 39716 on 2017/5/3.
 */

public class MylistView extends ListView {
    public MylistView(Context context) {
        super(context);
        init(context);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    public MylistView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MylistView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private int screenHeight, screenWidth;
    private float nowScal = 1;
    private int firstY;
    private void init(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        defaultDisplay.getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;
        iv = new ImageView(context);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(screenWidth, screenWidth * screenWidth / screenHeight);
        iv.setLayoutParams(params);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addHeaderView(iv);
        iv.setImageResource(R.mipmap.xixi);
    }
    private ImageView iv;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            firstY = (int) ev.getY();
        }else if(action == MotionEvent.ACTION_MOVE){
            float nowY = ev.getY();
                nowScal = (nowY - firstY) / screenHeight + 1;
                AbsListView.LayoutParams params = new AbsListView.LayoutParams((int) (screenWidth), (int) (nowScal * screenWidth * screenWidth / screenHeight));
                iv.setLayoutParams(params);
        }
        return super.onTouchEvent(ev);
    }
}
