package com.github.megatronking.funnydraw.sample;

import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.LineMotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawerSet;
import com.github.megatronking.funnydraw.draw.OvalMotionDrawer;
import com.github.megatronking.funnydraw.draw.QuadBezierMotionDrawer;

/**
 * Draw a wine glass on google ai canvas.
 *
 * @author Magetron King
 * @since 18/7/28 00:04
 */
public class WineGlassSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(Canvas canvas) {
        int topOvalRadiusX = 200;
        int topOvalRadiusY = 100;
        int topOvalCenterX = canvas.centerX;
        int topOvalCenterY = canvas.centerY - 400;
        OvalMotionDrawer drawer1 = new OvalMotionDrawer(topOvalCenterX, topOvalCenterY,
                topOvalRadiusX, topOvalRadiusY, 0, 1000);

        int bottomOvalRadiusX = 100;
        int bottomOvalRadiusY = 50;
        int bottomOvalCenterX = canvas.centerX;
        int bottomOvalCenterY = canvas.centerY + 400;
        OvalMotionDrawer drawer2 = new OvalMotionDrawer(bottomOvalCenterX, bottomOvalCenterY,
                bottomOvalRadiusX, bottomOvalRadiusY, 0, 1000);

        int glassBottomX = canvas.centerX;
        int glassBottomY = canvas.centerY + 150;
        QuadBezierMotionDrawer drawer3 = new QuadBezierMotionDrawer(topOvalCenterX - topOvalRadiusX,
                topOvalCenterY, glassBottomX, glassBottomY, canvas.left, canvas.centerY, 1000);
        QuadBezierMotionDrawer drawer4 = new QuadBezierMotionDrawer(topOvalCenterX + topOvalRadiusX,
                topOvalCenterY, glassBottomX, glassBottomY, canvas.right, canvas.centerY, 1000);

        LineMotionDrawer drawer5 = new LineMotionDrawer(glassBottomX, glassBottomY,
                bottomOvalCenterX, bottomOvalCenterY - bottomOvalRadiusY, 500);

        return new MotionDrawerSet(drawer1, drawer2, drawer3, drawer4, drawer5);
    }

}
