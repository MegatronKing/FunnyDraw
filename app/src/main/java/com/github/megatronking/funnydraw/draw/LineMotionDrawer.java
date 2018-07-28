package com.github.megatronking.funnydraw.draw;

import android.graphics.Point;

/**
 * Draw line with motion events.
 *
 * @author Magetron King
 * @since 18/7/26 20:02
 */

public class LineMotionDrawer extends AbstractMotionDrawer {

    private int mStartX;
    private int mStartY;
    private int mEndX;
    private int mEndY;

    private float mDistance;

    private boolean mIsSmoothly;

    public LineMotionDrawer(Point start, Point end, int duration) {
        this(start.x, start.y, end.x, end.y, duration);
    }

    public LineMotionDrawer(int startX, int startY, int endX, int endY, int duration) {
        super(duration);
        mStartX = startX;
        mStartY = startY;
        mEndX = endX;
        mEndY = endY;
        mDistance = (float) Math.sqrt((mEndX - mStartX) * (mEndX - mStartX) + (mEndY - mStartY) *
                (mEndY - mStartY));
    }

    @Override
    protected float[] getAnimateValues() {
        return new float[] {0, mDistance};
    }

    @Override
    protected void onDrawStart() {
        notifyMotionDown(mStartX, mStartY);
    }

    @Override
    protected void onDrawUnderway(float fraction) {
        boolean isOriginalSmoothly = mStartX == mEndX || mStartY == mEndY;
        if (mIsSmoothly && !isOriginalSmoothly) {
            if (fraction <= 0.5f) {
                notifyMotionMove(mStartX, mStartY);
            } else {
                notifyMotionMove(mEndX, mEndY);
            }
        } else {
            int moveX = (int) (mStartX + (mEndX - mStartX) * fraction);
            int moveY = (int) (mStartY + (mEndY - mStartY) * fraction);
            notifyMotionMove(moveX, moveY);
        }
    }

    @Override
    protected void onDrawEnd() {
        notifyMotionUp(mEndX, mEndY, true);
    }

    @Override
    public void draw(boolean isSmoothly) {
        mIsSmoothly = isSmoothly;
        super.draw(isSmoothly);
    }

}
