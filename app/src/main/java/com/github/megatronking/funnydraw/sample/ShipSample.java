package com.github.megatronking.funnydraw.sample;

import android.graphics.Point;
import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.LineMotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawerSet;

/**
 * Draw a ship on google ai canvas.
 *
 * @author Magetron King
 * @since 18/7/27 23:09
 */

public class ShipSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(Canvas canvas) {
        Point bodyLeftTop = new Point(canvas.centerX - 300, canvas.height * 5 / 6);
        Point bodyRightTop = new Point(canvas.centerX + 300, canvas.height * 5 / 6);
        Point bodyLeftBottom = new Point(canvas.centerX - 200, bodyLeftTop.y + 150);
        Point bodyRightBottom = new Point(canvas.centerX + 200, bodyRightTop.y + 150);
        LineMotionDrawer drawer1 = new LineMotionDrawer(bodyLeftTop, bodyRightTop, 1000);
        LineMotionDrawer drawer2 = new LineMotionDrawer(bodyRightTop, bodyRightBottom, 1000);
        LineMotionDrawer drawer3 = new LineMotionDrawer(bodyRightBottom, bodyLeftBottom, 1000);
        LineMotionDrawer drawer4 = new LineMotionDrawer(bodyLeftBottom, bodyLeftTop, 1000);

        Point mastTop = new Point(canvas.centerX, bodyLeftTop.y - 400);
        Point mastBottom = new Point(canvas.centerX, bodyLeftTop.y);
        LineMotionDrawer drawer5 = new LineMotionDrawer(mastTop, mastBottom, 1000);

        Point flagNeedle = new Point(mastTop.x + 100, mastTop.y + 100);
        Point flagBottom = new Point(mastTop.x, mastTop.y + 200);
        LineMotionDrawer drawer6 = new LineMotionDrawer(mastTop, flagNeedle, 1000);
        LineMotionDrawer drawer7 = new LineMotionDrawer(flagNeedle, flagBottom, 1000);
        return new MotionDrawerSet(drawer1, drawer2, drawer3, drawer4, drawer5,drawer6, drawer7);
    }

}
