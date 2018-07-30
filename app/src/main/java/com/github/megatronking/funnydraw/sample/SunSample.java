package com.github.megatronking.funnydraw.sample;

import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.CircleMotionDrawer;
import com.github.megatronking.funnydraw.draw.LineMotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawerSet;

/**
 * Draw a sun on google ai canvas.
 *
 * @author Magetron King
 * @since 18/7/27 19:42
 */

public class SunSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(@NonNull Canvas canvas) {
        int shineOffset1 = 250;
        int shineOffset2 = 400;
        int shineOffset3 = (int) (250 / Math.sqrt(2));
        int shineOffset4 = (int) (400 / Math.sqrt(2));
        CircleMotionDrawer drawer1 = new CircleMotionDrawer(canvas.centerX, canvas.centerY,
                200, 1000);
        LineMotionDrawer drawer2 = new LineMotionDrawer(canvas.centerX - shineOffset4,
                canvas.centerY - shineOffset4, canvas.centerX - shineOffset3,
                canvas.centerY - shineOffset3, 500);
        LineMotionDrawer drawer3 = new LineMotionDrawer(canvas.centerX + shineOffset4,
                canvas.centerY - shineOffset4, canvas.centerX + shineOffset3,
                canvas.centerY - shineOffset3, 500);
        LineMotionDrawer drawer4 = new LineMotionDrawer(canvas.centerX,
                canvas.centerY - shineOffset2, canvas.centerX , canvas.centerY - shineOffset1,
                500);
        LineMotionDrawer drawer5 = new LineMotionDrawer(canvas.centerX - shineOffset2,
                canvas.centerY, canvas.centerX - shineOffset1, canvas.centerY,
                500);
        LineMotionDrawer drawer6 = new LineMotionDrawer(canvas.centerX + shineOffset2,
                canvas.centerY, canvas.centerX + shineOffset1, canvas.centerY,
                500);
        LineMotionDrawer drawer7 = new LineMotionDrawer(canvas.centerX - shineOffset4,
                canvas.centerY + shineOffset4, canvas.centerX - shineOffset3,
                canvas.centerY + shineOffset3, 500);
        LineMotionDrawer drawer8 = new LineMotionDrawer(canvas.centerX + shineOffset4,
                canvas.centerY + shineOffset4, canvas.centerX + shineOffset3,
                canvas.centerY + shineOffset3, 500);
        LineMotionDrawer drawer9 = new LineMotionDrawer(canvas.centerX,
                canvas.centerY + shineOffset2, canvas.centerX , canvas.centerY + shineOffset1,
                500);
        return new MotionDrawerSet(drawer1, drawer2, drawer3, drawer4, drawer5, drawer6, drawer7,
                drawer8, drawer9);
    }

}
