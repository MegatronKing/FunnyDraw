package com.github.megatronking.funnydraw.draw;

/**
 * Draw circle with motion events.
 *
 * @author Magetron King
 * @since 18/7/26 20:02
 */

public class CircleMotionDrawer extends AbstractMotionDrawer {

    private int mCenterX;
    private int mCenterY;
    private int mRadius;

    public CircleMotionDrawer(int centerX, int centerY, int radius, int duration) {
        super(duration);
        mCenterX = centerX;
        mCenterY = centerY;
        mRadius = radius;
    }

    @Override
    protected void onDrawStart() {
        // Start from top center.
        notifyMotionDown(mCenterX, mCenterY - mRadius);
    }

    @Override
    protected void onDrawUnderway(float fraction) {
        float corner = (float) (2 * Math.PI * fraction);
        notifyMotionMove((int) (mCenterX + mRadius * Math.sin(corner)),
                (int) (mCenterY - mRadius * Math.cos(corner)));
    }

    @Override
    protected void onDrawEnd() {
        // End to top center.
        notifyMotionUp(mCenterX, mCenterY - mRadius, true);
    }

    @Override
    protected float[] getAnimateValues() {
        return new float[] {0, 360};
    }

}
