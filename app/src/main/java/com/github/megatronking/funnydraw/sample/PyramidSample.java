package com.github.megatronking.funnydraw.sample;

import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.TriangleMotionDrawer;

/**
 * Draw a pyramid on google ai canvas.
 *
 * @author Magetron King
 * @since 18/7/28 12:08
 */

public class PyramidSample implements Sample {

    @NonNull
    @Override
    public MotionDrawer buildDrawer(@NonNull Canvas canvas) {
        return new TriangleMotionDrawer(canvas.centerX, canvas.centerY - 200,
                canvas.centerX - 200, canvas.centerY + 200,
                canvas.centerX + 200, canvas.centerY + 200, 3000);
    }

}
