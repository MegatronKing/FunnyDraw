package com.github.megatronking.funnydraw.draw;

import android.graphics.Path;
import android.os.Build;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Draw curve with motion events.
 *
 * @author Magetron King
 * @since 18/7/26 20:02
 */

public abstract class CurveMotionDrawer extends AbstractMotionDrawer {

    /**
     * Governs the accuracy of the approximation of the {@link Path}.
     */
    private static final float PRECISION = 0.002f;

    private float[] mX;
    private float[] mY;

    /**
     * Create a path based on curve points.
     *
     * @return A curve path.
     */
    protected abstract Path curveToPath();

    public CurveMotionDrawer(int duration) {
        super(duration);
    }

    @Override
    protected void onDrawStart() {
        notifyMotionDown((int)mX[0], (int)mY[0]);
    }

    @Override
    protected void onDrawUnderway(float fraction) {
        int index = (int) ((mX.length - 1) * fraction);
        notifyMotionMove((int) mX[index], (int) mY[index]);
    }

    @Override
    protected void onDrawEnd() {
        notifyMotionUp((int)mX[mX.length - 1], (int)mY[mY.length - 1], true);
    }

    @Override
    public void draw(boolean isSmoothly) {
        float[] pointComponents = approximate(curveToPath());
        if (pointComponents == null) {
            mX = new float[]{0};
            mY = new float[]{0};
        } else {
            int numPoints = pointComponents.length / 3;
            mX = new float[numPoints];
            mY = new float[numPoints];
            int componentIndex = 0;
            for (int i = 0; i < numPoints; i++) {
                componentIndex++;
                float x = pointComponents[componentIndex++];
                float y = pointComponents[componentIndex++];
                mX[i] = x;
                mY[i] = y;
            }
        }
        super.draw(isSmoothly);
    }

    @Override
    protected float[] getAnimateValues() {
        return new float[] {0, mX.length - 1};
    }

    private float[] approximate(Path path) {
        float[] points = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            points = path.approximate(PRECISION);
        } else {
            try {
                Method method = path.getClass().getDeclaredMethod("approximate", float.class);
                method.setAccessible(true);
                points = (float[]) method.invoke(path, PRECISION);
            } catch (NoSuchMethodException | InvocationTargetException| IllegalAccessException e) {
                // impossible
            }
        }
        return points;
    }

}
