package com.hc.renderscript;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.a39716.tt.R;

/*
ac dispatch
pa dispatch
pa onintercept
son dispatch

ac dispatch
pa dispatch
pa onintercept
son dispatch
 */
public class ActivityTouchTest extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    ParentView activity_touch_test;
    View bu1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_test);
        activity_touch_test = (ParentView) findViewById(R.id.activity_touch_test);
        activity_touch_test.setOnClickListener(this);
        activity_touch_test.setOnTouchListener(this);
        bu1 = findViewById(R.id.bu1);
        bu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("hx", "onClick bu1");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        Log.i("hx", "activity onTouchEvent "+b);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.i("hx", "activity dispatchTouchEvent "+b);
        return false;
    }

    @Override
    public void onClick(View v) {
        Log.i("hx", "pp onClick ");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("hx", "pp onTouch ");
        return false;
    }
}
