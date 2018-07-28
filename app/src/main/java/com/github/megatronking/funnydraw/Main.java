package com.github.megatronking.funnydraw;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.Looper;
import android.view.IWindowManager;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.http.CommandServer;
import com.github.megatronking.funnydraw.sample.SampleManager;

import java.lang.reflect.Method;

/**
 * A server running in app_process, we can execute adb command without grant system permission.
 *
 * @author Magetron King
 * @since 18/7/23 11:41
 */

public final class Main {

    public static void main(String[] args) throws Exception {
        Looper.prepare();
        calculateCanvas();
        CommandServer.start();
        Looper.loop();
    }

    private static void calculateCanvas() throws Exception {
        Method getServiceMethod = Class.forName("android.os.ServiceManager").
                getDeclaredMethod("getService", String.class);
        Point displaySize = new Point();
        IWindowManager wm = IWindowManager.Stub.asInterface((IBinder)
                getServiceMethod.invoke(null, Context.WINDOW_SERVICE));
        wm.getBaseDisplaySize(0, displaySize);
        Rect canvasRect = new Rect(100, 400, displaySize.x - 100, displaySize.y);
        Canvas canvas = new Canvas(canvasRect.width(), canvasRect.height(), canvasRect.centerX(),
                canvasRect.centerY());
        SampleManager.get().setCanvas(canvas);
    }

}
