package com.github.megatronking.funnydraw.sample;

import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.OvalMotionDrawer;

/**
 * Draw a oval on google ai canvas.
 *
 * @author Magetron King
 * @since 18/7/27 21:42
 */

public class OvalSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(Canvas canvas) {
        return new OvalMotionDrawer(canvas.centerX, canvas.centerY, canvas.width / 4,
                canvas.height / 4, 0, DEFAULT_DURATION);
    }

}
