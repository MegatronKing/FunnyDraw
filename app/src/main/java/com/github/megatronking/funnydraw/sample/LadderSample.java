package com.github.megatronking.funnydraw.sample;

import android.graphics.Point;
import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.LineMotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawerSet;

/**
 * Draw a ladder on google ai canvas.
 *
 * @author Magetron King
 * @since 18/7/27 23:09
 */

public class LadderSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(@NonNull Canvas canvas) {
        Point ladderLeftTop = new Point(canvas.centerX - 100, canvas.top + 300);
        Point ladderLeftBottom = new Point(canvas.centerX - 100, canvas.bottom - 300);
        Point ladderRightTop = new Point(canvas.centerX + 100, canvas.top + 300);
        Point ladderRightBottom = new Point(canvas.centerX + 100, canvas.bottom - 300);
        LineMotionDrawer drawer1 = new LineMotionDrawer(ladderLeftTop, ladderLeftBottom, 1000);
        LineMotionDrawer drawer2 = new LineMotionDrawer(ladderRightTop, ladderRightBottom, 1000);

        Point whippletree1 = new Point(ladderLeftTop.x, ladderLeftTop.y
                + (ladderLeftBottom.y - ladderLeftTop.y) / 4);
        Point whippletree2 = new Point(ladderRightTop.x, ladderRightTop.y
                + (ladderRightBottom.y - ladderRightTop.y) / 4);
        LineMotionDrawer drawer3 = new LineMotionDrawer(whippletree1, whippletree2, 500);

        Point whippletree3 = new Point(ladderLeftTop.x, ladderLeftTop.y
                + (ladderLeftBottom.y - ladderLeftTop.y) * 2 / 4);
        Point whippletree4 = new Point(ladderRightTop.x, ladderRightTop.y
                + (ladderRightBottom.y - ladderRightTop.y) * 2 / 4);
        LineMotionDrawer drawer4 = new LineMotionDrawer(whippletree3, whippletree4, 500);

        Point whippletree5 = new Point(ladderLeftTop.x, ladderLeftTop.y
                + (ladderLeftBottom.y - ladderLeftTop.y) * 3 / 4);
        Point whippletree6 = new Point(ladderRightTop.x, ladderRightTop.y
                + (ladderRightBottom.y - ladderRightTop.y) * 3 / 4);
        LineMotionDrawer drawer5 = new LineMotionDrawer(whippletree5, whippletree6, 500);

        return new MotionDrawerSet(drawer1, drawer2, drawer3, drawer4, drawer5);
    }

}
