package com.github.megatronking.funnydraw.utils;

import android.graphics.Point;

/**
 * Utils for graphs.
 *
 * @author Sundy
 * @since 18/7/29 10:59
 */

public final class GraphUtils {

    public static Point calculateCrossPoint(Point p1, Point p2, Point p3, Point p4) {
        float a1 = p1.x == p2.x ? 0 : (float)(p1.y - p2.y) / (float)(p1.x - p2.x);
        float b1 = p1.y - a1 * p1.x;
        float a2 = p3.x == p4.x ? 0 : (float)(p3.y - p4.y) / (float)(p3.x - p4.x);
        float b2 = p3.y - a2 * p3.x;
        int x = (int) ((b1 - b2) / (a2 - a1));
        int y = (int) (a1 * x + b1);
        return new Point(x, y);
    }

}
