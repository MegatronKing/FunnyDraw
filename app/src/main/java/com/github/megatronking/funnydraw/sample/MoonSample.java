package com.github.megatronking.funnydraw.sample;

import android.graphics.Point;
import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawerSet;
import com.github.megatronking.funnydraw.draw.QuadBezierMotionDrawer;

/**
 * Draw a moon on google ai canvas.
 *
 * @author Magetron King
 * @since 18/08/01 19:42
 */

public class MoonSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(@NonNull Canvas canvas) {
        Point p = new Point(canvas.centerX - 150, canvas.centerY - 300);
        Point p2 = new Point(canvas.centerX + 150, canvas.centerY + 300);
        Point control1 = new Point(canvas.centerX - 200, canvas.centerY + 100);
        Point control2 = new Point(canvas.centerX - 400, canvas.centerY + 200);
        QuadBezierMotionDrawer drawer1 = new QuadBezierMotionDrawer(p, p2, control1, 1000);
        QuadBezierMotionDrawer drawer2 = new QuadBezierMotionDrawer(p, p2, control2, 1000);
        return new MotionDrawerSet(drawer1, drawer2);
    }

}
