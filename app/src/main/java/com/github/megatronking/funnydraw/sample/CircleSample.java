package com.github.megatronking.funnydraw.sample;

import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.CircleMotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawer;

/**
 * Draw a circle on google ai canvas.
 *
 * @author Magetron King
 * @since 18/7/27 19:42
 */

public class CircleSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(@NonNull Canvas canvas) {
        return new CircleMotionDrawer(canvas.centerX, canvas.centerY, canvas.width / 4,
                DEFAULT_DURATION);
    }

}
