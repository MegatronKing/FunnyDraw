package com.github.megatronking.funnydraw.draw;

import android.graphics.Point;
import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.utils.GraphUtils;

/**
 * Serial multi lines motion drawer, connects the lines by sort.
 *
 * @author Sundy
 * @since 18/7/29 13:16
 */

public class SerialLinesMotionDrawer implements MotionDrawer {

    private MotionDrawerSet mLineSet;

    public SerialLinesMotionDrawer(@NonNull Point[] points, int duration) {
        if (points.length <= 1) {
            throw new IllegalArgumentException("SerialLinesMotionDrawer requires multiple points");
        }
        MotionDrawer[] lineDrawers = new MotionDrawer[points.length - 1];
        float totalDistance = GraphUtils.distance(points);
        for (int i = 0; i < points.length; i++) {
            if (i == 0) {
                continue;
            }
            float distance = GraphUtils.distance(points[i - 1], points[i]);
            int childDuration = (int) (duration * distance / totalDistance);
            lineDrawers[i - 1] = new LineMotionDrawer(points[i - 1], points[i], childDuration);
        }
        mLineSet = new MotionDrawerSet(lineDrawers);
    }

    @Override
    public void setCallback(@NonNull MotionDrawerCallback callback) {
        mLineSet.setCallback(callback);
    }

    @Override
    public void draw(boolean isSmoothly) {
        mLineSet.draw(isSmoothly);
    }

}
