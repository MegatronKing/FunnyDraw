package com.github.megatronking.funnydraw.draw;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * An abstract motion drawer. We use the drawer to draw various graphs.
 *
 * @author Magetron King
 * @since 18/7/26 16:09
 */

public abstract class AbstractMotionDrawer implements MotionDrawer {

    private ValueAnimator mDrawAnimator;

    private MotionDrawerCallback mCallback;

    private long mDownTime;

    /**
     * Invoked when the draw is started.
     */
    protected abstract void onDrawStart();

    /**
     * Invoked when the draw is underway.
     *
     * @param fraction Returns the current draw progress fraction, from 0 to 1.0.
     */
    protected abstract void onDrawUnderway(float fraction);

    /**
     * Invoked when the draw is ended.
     */
    protected abstract void onDrawEnd();

    public AbstractMotionDrawer(int duration) {
        this.mDrawAnimator = new ValueAnimator();
        this.mDrawAnimator.setDuration(duration);
        this.mDrawAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                onDrawEnd();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mDownTime = SystemClock.uptimeMillis();
                onDrawStart();
            }
        });
        this.mDrawAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                onDrawUnderway(animation.getAnimatedFraction());
            }
        });
    }

    /**
     * Set the draw callback.
     *
     * @param callback Callback with different MotionEvents.
     */
    public void setCallback(@NonNull MotionDrawerCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void draw(boolean isSmoothly) {
        mDrawAnimator.setFloatValues(getAnimateValues());
        mDrawAnimator.setInterpolator(getInterpolator());
        mDrawAnimator.start();
    }

    /**
     * Return an interpolator for the draw curve.
     *
     * @return An interpolator, default is {@link LinearInterpolator}.
     */
    protected Interpolator getInterpolator() {
        return new LinearInterpolator();
    }

    /**
     * Sets float values that will be animated between.
     *
     * @return A set of values that the animation will animate between over time.
     */
    protected float[] getAnimateValues() {
        return new float[] {0, mDrawAnimator.getDuration()};
    }

    /**
     * Notify the motion down event is active.
     *
     * @param x Coordinate x.
     * @param y Coordinate y.
     */
    protected void notifyMotionDown(int x, int y) {
        notifyMotionDraw(MotionEventFactory.create(MotionEvent.ACTION_DOWN, mDownTime, x, y), false);
    }

    /**
     * Notify the motion move event is active.
     *
     * @param x Coordinate x.
     * @param y Coordinate y.
     */
    protected void notifyMotionMove(int x, int y) {
        notifyMotionDraw(MotionEventFactory.create(MotionEvent.ACTION_MOVE, mDownTime, x, y), false);
    }

    /**
     * Notify the motion up event is active.
     *
     * @param x Coordinate x.
     * @param y Coordinate y.
     * @param isFinished Whether the draw is finished.
     */
    protected void notifyMotionUp(int x, int y, boolean isFinished) {
        notifyMotionDraw(MotionEventFactory.create(MotionEvent.ACTION_UP, mDownTime, x, y), isFinished);
    }

    private void notifyMotionDraw(MotionEvent event, boolean isFinished) {
        mCallback.onDraw(event, isFinished);
    }

}
