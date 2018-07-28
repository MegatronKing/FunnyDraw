package com.github.megatronking.funnydraw.sample;

import android.graphics.Rect;
import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.LineMotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawerSet;

/**
 * Draw a door on google ai canvas.
 *
 * @author Magetron King
 * @since 18/7/28 15:42
 */

public class DoorSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(Canvas canvas) {
        Rect rect = new Rect(canvas.centerX - 150, canvas.centerY - 200,
                canvas.centerX + 150, canvas.centerY + 200);
        LineMotionDrawer drawer1 = new LineMotionDrawer(rect.left, rect.top, rect.right, rect.top,
                1000);
        LineMotionDrawer drawer2 = new LineMotionDrawer(rect.left, rect.top, rect.left, rect.bottom,
                1000);
        LineMotionDrawer drawer3 = new LineMotionDrawer(rect.right, rect.top, rect.right, rect.bottom,
                1000);
        return new MotionDrawerSet(drawer1, drawer2, drawer3);
    }

}
