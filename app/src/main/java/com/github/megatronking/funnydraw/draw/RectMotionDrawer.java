package com.github.megatronking.funnydraw.draw;

import android.graphics.Rect;
import android.support.annotation.NonNull;

/**
 * Draw rect with motion events.
 *
 * @author Magetron King
 * @since 18/7/26 21:14
 */

public class RectMotionDrawer implements MotionDrawer {

    private MotionDrawerSet mSetDrawer;

    public RectMotionDrawer(@NonNull Rect mRect, int duration) {
        int perimeter = (mRect.width() + mRect.height()) * 2;
        LineMotionDrawer drawer1 = new LineMotionDrawer(mRect.left, mRect.top, mRect.right,
                mRect.top, duration * mRect.width() / perimeter);
        LineMotionDrawer drawer2 = new LineMotionDrawer(mRect.right, mRect.top, mRect.right,
                mRect.bottom, duration * mRect.height() / perimeter);
        LineMotionDrawer drawer3 = new LineMotionDrawer(mRect.right, mRect.bottom, mRect.left,
                mRect.bottom, duration * mRect.width() / perimeter);
        LineMotionDrawer drawer4 = new LineMotionDrawer(mRect.left, mRect.bottom, mRect.left,
                mRect.top, duration * mRect.height() / perimeter);
        mSetDrawer = new MotionDrawerSet(drawer1, drawer2, drawer3, drawer4);
    }

    @Override
    public void setCallback(@NonNull MotionDrawerCallback callback) {
        mSetDrawer.setCallback(callback);
    }

    @Override
    public void draw(boolean isSmoothly) {
        // The original draw is smoothly.
        mSetDrawer.draw(false);
    }

}
