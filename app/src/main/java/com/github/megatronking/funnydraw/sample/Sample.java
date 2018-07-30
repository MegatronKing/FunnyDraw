package com.github.megatronking.funnydraw.sample;

import android.support.annotation.NonNull;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.MotionDrawer;

/**
 * The base sample class to be implement by sub class.
 *
 * <strong>
 *     Please note that the sub class must provide a non-param constructor method.
 * </strong>
 *
 * @author Magetron King
 * @since 18/7/27 21:38
 */

public interface Sample {

    /**
     * The default draw duration.
     */
    int DEFAULT_DURATION = 3000;

    /**
     * Build the implemented motion drawer.
     *
     * @param canvas Canvas information.
     */
    @NonNull
    MotionDrawer buildDrawer(@NonNull Canvas canvas);

}
