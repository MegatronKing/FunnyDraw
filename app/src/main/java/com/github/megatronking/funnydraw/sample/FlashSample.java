package com.github.megatronking.funnydraw.sample;

import android.graphics.Point;
import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.SerialLinesMotionDrawer;

/**
 * Draw a flash on google ai canvas.
 *
 * @author Sundy
 * @since 18/7/29 13:12
 */

public class FlashSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(Canvas canvas) {
        Point p1 = new Point(canvas.centerX, canvas.centerY - 400);
        Point p2 = new Point(canvas.centerX - 150, canvas.centerY);
        Point p3 = new Point(canvas.centerX + 150, canvas.centerY);
        Point p4 = new Point(canvas.centerX, canvas.centerY + 400);
        return new SerialLinesMotionDrawer(new Point[]{p1, p2, p3, p4}, 3000);
    }

}
