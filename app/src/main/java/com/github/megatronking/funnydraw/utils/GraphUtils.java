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

    public static float distance(int p1x, int p1y, int p2x, int p2y) {
        return distance(new Point(p1x, p1y), new Point(p2x, p2y));
    }

    public static float distance(Point p1, Point p2) {
        return (float) Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    public static float distance(Point[] points) {
        float totalDistance = 0;
        Point lastPoint = points[0];
        for (Point p : points) {
            if (p == lastPoint) {
                continue;
            }
            totalDistance += GraphUtils.distance(lastPoint, p);
            lastPoint = p;
        }
        return totalDistance;
    }

}
