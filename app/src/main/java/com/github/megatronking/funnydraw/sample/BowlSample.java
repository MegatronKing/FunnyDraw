package com.github.megatronking.funnydraw.sample;

import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.LineMotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawerSet;
import com.github.megatronking.funnydraw.draw.OvalMotionDrawer;
import com.github.megatronking.funnydraw.draw.QuadBezierMotionDrawer;

/**
 * Draw a bowl on google ai canvas.
 *
 * @author Sundy
 * @since 18/7/29 11:44
 */

public class BowlSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(@NonNull Canvas canvas) {
        int topOvalRadiusX = 300;
        int topOvalRadiusY = 80;
        int topOvalCenterX = canvas.centerX;
        int topOvalCenterY = canvas.centerY - 100;
        OvalMotionDrawer drawer1 = new OvalMotionDrawer(topOvalCenterX, topOvalCenterY,
                topOvalRadiusX, topOvalRadiusY, 0, 1000);

        int bowlLeftBottomX = canvas.centerX - 80;
        int bowlLeftBottomY = canvas.centerY + 100;
        int bowlRightBottomX = canvas.centerX + 80;
        int bowlRightBottomY = canvas.centerY + 100;

        QuadBezierMotionDrawer drawer2 = new QuadBezierMotionDrawer(topOvalCenterX - topOvalRadiusX,
                topOvalCenterY, bowlLeftBottomX, bowlLeftBottomY, canvas.left + 200, canvas.centerY, 1000);
        QuadBezierMotionDrawer drawer3 = new QuadBezierMotionDrawer(topOvalCenterX + topOvalRadiusX,
                topOvalCenterY, bowlRightBottomX, bowlRightBottomY, canvas.right - 200, canvas.centerY, 1000);

        LineMotionDrawer drawer4 = new LineMotionDrawer(bowlLeftBottomX, bowlLeftBottomY,
                bowlRightBottomX, bowlRightBottomY, 500);

        return new MotionDrawerSet(drawer1, drawer2, drawer3, drawer4);
    }

}
