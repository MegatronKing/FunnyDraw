package com.github.megatronking.funnydraw.draw;

/**
 * Draw oval with motion events.
 *
 * @author Magetron King
 * @since 18/7/26 20:02
 */

public class OvalMotionDrawer extends AbstractMotionDrawer {

    private int mCenterX;
    private int mCenterY;
    private int mRadiusX;
    private int mRadiusY;
    private int mRotate;

    public OvalMotionDrawer(int centerX, int centerY, int radiusX, int radiusY, int rotate,
                            int duration) {
        super(duration);
        mCenterX = centerX;
        mCenterY = centerY;
        mRadiusX = radiusX;
        mRadiusY = radiusY;
        mRotate = rotate;
    }

    @Override
    protected void onDrawStart() {
        float rotate = (float) (Math.PI * 2 * mRotate / 360);
        int x = (int) (mCenterX - mRadiusY * Math.sin(rotate));
        int y = (int) (mCenterY - mRadiusY * Math.cos(rotate));
        notifyMotionDown(x, y);
    }

    @Override
    protected void onDrawUnderway(float fraction) {
        // x = X + a * sint * cosθ - b * cost * sinθ
        // y = Y + a * sint * sinθ - b * cost * cosθ
        // t is corner
        // θ is rotate
        float corner = (float) (2 * Math.PI * fraction);
        float rotate = (float) (Math.PI * 2 * mRotate / 360);
        int x = (int) (mCenterX + mRadiusX * Math.sin(corner) * Math.cos(rotate) -
                mRadiusY * Math.cos(corner) * Math.sin(rotate));
        int y = (int) (mCenterY + mRadiusX * Math.sin(corner) * Math.sin(rotate) -
                        mRadiusY * Math.cos(corner) * Math.cos(rotate));
        notifyMotionMove(x, y);
    }

    @Override
    protected void onDrawEnd() {
        float rotate = (float) (Math.PI * 2 * mRotate / 360);
        int x = (int) (mCenterX - mRadiusY * Math.sin(rotate));
        int y = (int) (mCenterY - mRadiusY * Math.cos(rotate));
        notifyMotionUp(x, y, true);
    }

    @Override
    protected float[] getAnimateValues() {
        return new float[] {0, 360};
    }

}
