package com.github.megatronking.funnydraw.draw;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Draw quadratic bezier with motion events.
 *
 * @author Magetron King
 * @since 18/7/26 20:02
 */

public class QuadBezierMotionDrawer extends CurveMotionDrawer {

    private int mStartX;
    private int mStartY;
    private int mEndX;
    private int mEndY;
    private int mControlX;
    private int mControlY;

    public QuadBezierMotionDrawer(Point start, Point end, Point control, int duration) {
        this(start.x, start.y, end.x, end.y, control.x, control.y, duration);
    }

    public QuadBezierMotionDrawer(int startX, int startY, int endX, int endY, int controlX, int controlY,
                                   int duration) {
        super(duration);
        mStartX = startX;
        mStartY = startY;
        mEndX = endX;
        mEndY = endY;
        mControlX = controlX;
        mControlY = controlY;
    }

    @Override
    protected Path curveToPath() {
        Path path = new Path();
        path.moveTo(mStartX, mStartY);
        path.quadTo(mControlX, mControlY, mEndX, mEndY);
        return path;
    }

}
