package com.github.megatronking.funnydraw.utils;

/**
 * Log utils.
 *
 * @author Magetron King
 * @since 18/7/27 19:44
 */

public final class Log {

    private static final String TAG = "FunnyDraw";

    private Log() {
    }

    public static void v(String msg) {
        android.util.Log.v(TAG, msg);
    }

    public static void d(String msg) {
        android.util.Log.d(TAG, msg);
    }

    public static void i(String msg) {
        android.util.Log.i(TAG, msg);
    }

    public static void e(String msg) {
        android.util.Log.e(TAG, msg);
    }

    public static void w(String msg) {
        android.util.Log.w(TAG, msg);
    }

    public static void wtf(Throwable throwable) {
        android.util.Log.wtf(TAG, throwable);
    }

}
