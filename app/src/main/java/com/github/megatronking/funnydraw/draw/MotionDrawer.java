package com.github.megatronking.funnydraw.draw;

import android.support.annotation.NonNull;

/**
 * A drawer can draw graphs using motion events through input manager in phone screen.
 *
 * @author Magetron King
 * @since 18/7/26 20:05
 */

public interface MotionDrawer {

    /**
     * Set the draw callback.
     *
     * @param callback Callback with different MotionEvents.
     */
    void setCallback(@NonNull MotionDrawerCallback callback);

    /**
     * Start drawing graph with a callback {@link MotionDrawerCallback} invoked.
     *
     * @param isSmoothly Should the draw smoothly.
     */
    void draw(boolean isSmoothly);

}
