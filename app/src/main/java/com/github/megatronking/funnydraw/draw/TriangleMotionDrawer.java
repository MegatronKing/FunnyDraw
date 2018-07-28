package com.github.megatronking.funnydraw.draw;

import android.graphics.Point;
import android.support.annotation.NonNull;

/**
 * Draw triangle with motion events.
 *
 * @author Magetron King
 * @since 18/7/26 22:14
 */

public class TriangleMotionDrawer implements MotionDrawer {

    private MotionDrawerSet mSetDrawer;

    public TriangleMotionDrawer(Point vertex1, Point vertex2, Point vertex3, int duration) {
        this(vertex1.x, vertex1.y, vertex2.x, vertex2.y, vertex3.x, vertex3.y, duration);
    }

    public TriangleMotionDrawer(int vertexX1, int vertexY1, int vertexX2, int vertexY2,
                                int vertexX3, int vertexY3, int duration) {
        int a = (int) (Math.sqrt((vertexX1 - vertexX2) * (vertexX1 - vertexX2) +
                (vertexY1 - vertexY2) * (vertexY1 - vertexY2)));
        int b = (int) (Math.sqrt((vertexX2 - vertexX3) * (vertexX2 - vertexX3) +
                (vertexY2 - vertexY3) * (vertexY2 - vertexY3)));
        int c = (int) (Math.sqrt((vertexX3 - vertexX1) * (vertexX3 - vertexX1) +
                (vertexY3 - vertexY1) * (vertexY3 - vertexY1)));
        int perimeter = a + b + c;
        LineMotionDrawer drawer1 = new LineMotionDrawer(vertexX1, vertexY1, vertexX2,
                vertexY2, duration * a / perimeter);
        LineMotionDrawer drawer2 = new LineMotionDrawer(vertexX2, vertexY2, vertexX3,
                vertexY3, duration * b / perimeter);
        LineMotionDrawer drawer3 = new LineMotionDrawer(vertexX3, vertexY3, vertexX1,
                vertexY1, duration * c / perimeter);
        mSetDrawer = new MotionDrawerSet(drawer1, drawer2, drawer3);
    }

    @Override
    public void setCallback(@NonNull MotionDrawerCallback callback) {
        mSetDrawer.setCallback(callback);
    }

    @Override
    public void draw(boolean isSmoothly) {
        mSetDrawer.draw(isSmoothly);
    }

}
