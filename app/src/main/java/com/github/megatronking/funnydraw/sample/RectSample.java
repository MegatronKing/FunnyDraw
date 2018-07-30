package com.github.megatronking.funnydraw.sample;

import android.graphics.Rect;
import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.RectMotionDrawer;

/**
 * Draw a rect on google ai canvas.
 *
 * @author Magetron King
 * @since 18/7/27 21:42
 */

public class RectSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(@NonNull Canvas canvas) {
        Rect rect = new Rect(canvas.centerX - canvas.width / 4, canvas.centerY - canvas.height / 4,
                canvas.centerX + canvas.width / 4, canvas.centerY + canvas.height / 4);
        return new RectMotionDrawer(rect, DEFAULT_DURATION);
    }

}
