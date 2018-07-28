package com.github.megatronking.funnydraw.draw;

import android.support.annotation.NonNull;
import android.view.MotionEvent;

/**
 * Represents a group of MotionDrawer that draw in order.
 *
 * @author Magetron King
 * @since 18/7/26 20:02
 */

public class MotionDrawerSet implements MotionDrawer {

    private OrderedMotionDrawer mOrderedMotionDrawer;

    public MotionDrawerSet(@NonNull MotionDrawer... drawers) {
        if (drawers.length == 0) {
            throw new RuntimeException("MotionSetDrawer must have at lease one child drawer.");
        }
        this.mOrderedMotionDrawer = new OrderedMotionDrawer(drawers);
    }

    @Override
    public void setCallback(@NonNull MotionDrawerCallback callback) {
        mOrderedMotionDrawer.setCallback(callback);
    }

    @Override
    public void draw(boolean isSmoothly) {
        mOrderedMotionDrawer.draw(isSmoothly);
    }

    private class OrderedMotionDrawer implements MotionDrawer, MotionDrawerCallback {

        private MotionDrawer[] mDrawers;
        private int mIndex;

        private boolean mIsSmoothly;

        private MotionDrawerCallback mCallback;

        private OrderedMotionDrawer(MotionDrawer[] drawers) {
            this.mDrawers = drawers;
            for (MotionDrawer drawer : drawers) {
                drawer.setCallback(this);
            }
        }

        @Override
        public void setCallback(@NonNull MotionDrawerCallback callback) {
            mCallback = callback;
        }

        @Override
        public void draw(boolean isSmoothly) {
            mIsSmoothly = isSmoothly;
            mDrawers[mIndex].draw(isSmoothly);
            mIndex++;
        }

        @Override
        public void onDraw(@NonNull MotionEvent event, boolean isFinished) {
            boolean isAllFinished = isFinished && mIndex >= mDrawers.length;
            mCallback.onDraw(event, isAllFinished);
            if (isFinished && mIndex < mDrawers.length) {
                draw(mIsSmoothly);
            }
        }

    }

}
