package com.github.megatronking.funnydraw.sample;

import android.graphics.Point;
import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.LineMotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawerSet;

/**
 * Draw a umbrella on google ai canvas.
 *
 * @author Magetron King
 * @since 18/7/28 00:33
 */

public class UmbrellaSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(Canvas canvas) {
        Point topPoint = new Point(canvas.centerX, canvas.centerY - 300);
        Point leftPoint = new Point(canvas.centerX - 300, canvas.centerY);
        Point rightPoint = new Point(canvas.centerX + 300, canvas.centerY);
        LineMotionDrawer drawer1 = new LineMotionDrawer(topPoint, leftPoint, 1000);
        LineMotionDrawer drawer2 = new LineMotionDrawer(leftPoint, rightPoint, 1000);
        LineMotionDrawer drawer3 = new LineMotionDrawer(rightPoint, topPoint, 1000);
        LineMotionDrawer drawer4 = new LineMotionDrawer(topPoint,
                new Point(leftPoint.x + 150, leftPoint.y), 1000);
        LineMotionDrawer drawer5 = new LineMotionDrawer(topPoint,
                new Point(rightPoint.x - 150, rightPoint.y), 1000);
        LineMotionDrawer drawer6 = new LineMotionDrawer(topPoint,
                new Point(canvas.centerX, canvas.centerY + 400), 1000);
        return new MotionDrawerSet(drawer1, drawer2, drawer3, drawer4, drawer5, drawer6);
    }

}
