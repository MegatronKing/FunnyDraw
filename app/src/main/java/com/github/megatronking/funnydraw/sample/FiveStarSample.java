package com.github.megatronking.funnydraw.sample;

import android.graphics.Point;
import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.LineMotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawerSet;
import com.github.megatronking.funnydraw.utils.GraphUtils;

/**
 * Draw a five start graph on google ai canvas.
 *
 * @author Sundy
 * @since 18/7/29 09:48
 */

public class FiveStarSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(@NonNull Canvas canvas) {
        // Calculate five vertex points.
        int radius = 300;
        Point a = new Point(canvas.centerX, canvas.centerY - radius);
        Point b = new Point(canvas.centerX + (int)(radius * Math.cos(18 * Math.PI / 180)),
                canvas.centerY - (int)(radius * Math.sin(18 * Math.PI / 180)));
        Point c = new Point(canvas.centerX + (int)(radius * Math.cos(54 * Math.PI / 180)),
                canvas.centerY + (int)(radius * Math.sin(54 * Math.PI / 180)));
        Point d = new Point(canvas.centerX - (int)(radius * Math.cos(54 * Math.PI / 180)),
                canvas.centerY + (int)(radius * Math.sin(54 * Math.PI / 180)));
        Point e = new Point(canvas.centerX - (int)(radius * Math.cos(18 * Math.PI / 180)),
                canvas.centerY - (int)(radius * Math.sin(18 * Math.PI / 180)));
        // Calculate five pit points
        Point ap = GraphUtils.calculateCrossPoint(a, c, b, e);
        Point bp = GraphUtils.calculateCrossPoint(b, d, c, a);
        Point cp = GraphUtils.calculateCrossPoint(c, e, d, b);
        Point dp = GraphUtils.calculateCrossPoint(d, a, e, c);
        Point ep = GraphUtils.calculateCrossPoint(e, b, a, d);

        LineMotionDrawer drawer1 = new LineMotionDrawer(a, ap, 500);
        LineMotionDrawer drawer2 = new LineMotionDrawer(ap, b, 500);
        LineMotionDrawer drawer3 = new LineMotionDrawer(b, bp, 500);
        LineMotionDrawer drawer4 = new LineMotionDrawer(bp, c, 500);
        LineMotionDrawer drawer5 = new LineMotionDrawer(c, cp, 500);
        LineMotionDrawer drawer6 = new LineMotionDrawer(cp, d, 500);
        LineMotionDrawer drawer7 = new LineMotionDrawer(d, dp, 500);
        LineMotionDrawer drawer8 = new LineMotionDrawer(dp, e, 500);
        LineMotionDrawer drawer9 = new LineMotionDrawer(e, ep, 500);
        LineMotionDrawer drawer10 = new LineMotionDrawer(ep, a, 500);
        return new MotionDrawerSet(drawer1, drawer2, drawer3, drawer4, drawer5, drawer6, drawer7,
                drawer8, drawer9, drawer10);
    }

}
