package com.github.megatronking.funnydraw.draw;

import android.graphics.Point;
import android.os.SystemClock;
import android.support.v4.view.InputDeviceCompat;
import android.view.MotionEvent;

/**
 * Static factory creates MotionEvents.
 *
 * @author Magetron King
 * @since 18/7/26 19:18
 */

/* package */ final class MotionEventFactory {

    /* package */ static MotionEvent create(int action, long downTime, int x, int y) {
        MotionEvent event = MotionEvent.obtain(downTime, SystemClock.uptimeMillis(), action, x, y,
                1.0f,1.0f, 0, 1.0f, 1.0f, 0,
                0);
        event.setSource(InputDeviceCompat.SOURCE_TOUCHSCREEN);
        return event;
    }

    /* package */ static MotionEvent create(int action, long downTime, Point point) {
        return create(action, downTime, point.x, point.y);
    }

}
