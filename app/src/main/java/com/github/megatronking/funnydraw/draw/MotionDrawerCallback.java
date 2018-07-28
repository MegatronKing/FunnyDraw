package com.github.megatronking.funnydraw.draw;

import android.support.annotation.NonNull;
import android.view.MotionEvent;

/**
 * Callback invoked when motion event changed in drawing something.
 *
 * @author Magetron King
 * @since 18/7/26 16:05
 */

public interface MotionDrawerCallback {

    /**
     * The method invoke when a motion event should be input to input manager.
     *
     * @param event The motion event input to input manager.
     * @param isFinished Whether the draw is finished.
     */
    void onDraw(@NonNull MotionEvent event, boolean isFinished);

}
