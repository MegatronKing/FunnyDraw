package com.github.megatronking.funnydraw.draw;

import android.graphics.Path;

/**
 * Draw cubic bezier with motion events.
 *
 * @author Magetron King
 * @since 18/7/26 20:02
 */

public class CubicBezierMotionDrawer extends CurveMotionDrawer {

    private int mStartX;
    private int mStartY;
    private int mEndX;
    private int mEndY;
    private int mControlX1;
    private int mControlY1;
    private int mControlX2;
    private int mControlY2;

    public CubicBezierMotionDrawer(int startX, int startY, int endX, int endY, int controlX1, int controlY1,
                                  int controlX2, int controlY2, int duration) {
        super(duration);
        mStartX = startX;
        mStartY = startY;
        mEndX = endX;
        mEndY = endY;
        mControlX1 = controlX1;
        mControlY1 = controlY1;
        mControlX2 = controlX2;
        mControlY2 = controlY2;
    }

    @Override
    protected Path curveToPath() {
        Path path = new Path();
        path.moveTo(mStartX, mStartY);
        path.cubicTo(mControlX1, mControlY1, mControlX2, mControlY2, mEndX, mEndY);
        return path;
    }

}
