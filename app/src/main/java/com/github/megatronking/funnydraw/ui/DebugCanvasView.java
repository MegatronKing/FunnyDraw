package com.github.megatronking.funnydraw.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Receive motion events from motion drawers and display the path on this view.
 *
 * @author Magetron King
 * @since 18/7/28 10:01
 */

public class DebugCanvasView extends View {

    private static final int DEFAULT_PAINT_COLOR = Color.BLACK;
    private static final float DEFAULT_PAINT_STROKE_WIDTH = 10.0f;

    private Paint mPaint;
    private Path mMotionPath;

    private MotionEvent mLastMotionEvent;

    public DebugCanvasView(Context context) {
        this(context, null);
    }

    public DebugCanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DebugCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public DebugCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(DEFAULT_PAINT_COLOR);
        mPaint.setStrokeWidth(DEFAULT_PAINT_STROKE_WIDTH);
        mPaint.setStyle(Paint.Style.STROKE);

        mMotionPath = new Path();
    }

    public void drawMotionEventOnCanvas(MotionEvent event) {
        float x = event.getX() - getLeft();
        float y = event.getY() - getTop();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mMotionPath.moveTo(x, y);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (mLastMotionEvent == null) {
                mMotionPath.moveTo(x, y);
            }
            mMotionPath.lineTo(x, y);
        }
        mLastMotionEvent = event;
        invalidate();
    }

    public void clearCanvas() {
        mLastMotionEvent = null;
        mMotionPath.reset();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mMotionPath, mPaint);
    }

}
