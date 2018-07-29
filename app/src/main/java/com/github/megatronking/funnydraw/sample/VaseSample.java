package com.github.megatronking.funnydraw.sample;

import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.CubicBezierMotionDrawer;
import com.github.megatronking.funnydraw.draw.LineMotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawerSet;
import com.github.megatronking.funnydraw.draw.OvalMotionDrawer;

/**
 * Draw a vase on google ai canvas.
 *
 * @author Magetron King
 * @since 18/7/29 12:42
 */

public class VaseSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(Canvas canvas) {
        int topOvalRadiusX = 80;
        int topOvalRadiusY = 40;
        int topOvalCenterX = canvas.centerX;
        int topOvalCenterY = canvas.centerY - 350;
        OvalMotionDrawer drawer1 = new OvalMotionDrawer(topOvalCenterX, topOvalCenterY,
                topOvalRadiusX, topOvalRadiusY, 0, 1000);

        int bowlLeftBottomX = canvas.centerX - 80;
        int bowlLeftBottomY = canvas.centerY + 350;
        int bowlRightBottomX = canvas.centerX + 80;
        int bowlRightBottomY = canvas.centerY + 350;

        int leftControlPointX1 = canvas.centerX + 120;
        int leftControlPointY1 = canvas.centerY - 180;
        int leftControlPointX2 = canvas.centerX - 300;
        int leftControlPointY2 = canvas.centerY + 200;

        CubicBezierMotionDrawer drawer2 = new CubicBezierMotionDrawer(topOvalCenterX - topOvalRadiusX + 10,
                topOvalCenterY + 20, bowlLeftBottomX, bowlLeftBottomY,
                leftControlPointX1, leftControlPointY1, leftControlPointX2, leftControlPointY2,
                1000);

        int rightControlPointX1 = canvas.centerX - 120;
        int rightControlPointY1 = canvas.centerY - 180;
        int rightControlPointX2 = canvas.centerX + 300;
        int rightControlPointY2 = canvas.centerY + 200;
        CubicBezierMotionDrawer drawer3 = new CubicBezierMotionDrawer(topOvalCenterX + topOvalRadiusX - 10,
                topOvalCenterY + 20, bowlRightBottomX, bowlRightBottomY,
                rightControlPointX1, rightControlPointY1, rightControlPointX2, rightControlPointY2,
                1000);

        LineMotionDrawer drawer4 = new LineMotionDrawer(bowlLeftBottomX, bowlLeftBottomY,
                bowlRightBottomX, bowlRightBottomY, 500);

        return new MotionDrawerSet(drawer1, drawer2, drawer3, drawer4);

    }

}
