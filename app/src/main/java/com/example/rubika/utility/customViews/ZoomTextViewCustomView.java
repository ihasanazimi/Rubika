package com.example.rubika.utility.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import androidx.annotation.NonNull;

public class ZoomTextViewCustomView extends androidx.appcompat.widget.AppCompatTextView {
    private static final String TAG = "ZoomTextView";
    private ScaleGestureDetector mScaleDetector;

    private float mScaleFactor = 1.f;
    private float defaultSize;

    private float zoomLimit = 2.3f;


    public ZoomTextViewCustomView(Context context) {
        super(context);
        initialize();
    }

    public ZoomTextViewCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public ZoomTextViewCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        defaultSize = getTextSize();
        mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());

    }

    /***
     * @param zoomLimit
     * Default value is 3, 3 means text can zoom 3 times the default size
     */

    public void setZoomLimit(float zoomLimit) {
        this.zoomLimit = zoomLimit;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent ev) {
        super.onTouchEvent(ev);
        mScaleDetector.onTouchEvent(ev);
        return true;
    }

    /*Scale Gesture listener class,
    mScaleFactor is getting the scaling value
    and mScaleFactor is mapped between 1.0 and and zoomLimit
    that is 3.0 by default. You can also change it. 3.0 means text
    can zoom to 3 times the default value.*/

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(1.0f, Math.min(mScaleFactor, zoomLimit));
            setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultSize * mScaleFactor);
            Log.e(TAG, String.valueOf(mScaleFactor));
            return true;
        }
    }
}