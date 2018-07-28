package com.github.megatronking.funnydraw.utils;

import android.content.Context;

/**
 * Some common functions about dimen.
 *
 * @author Magetron King
 * @since 17/7/31 16:01
 */
public final class DimenUtils {

    private DimenUtils() {
        // private constructor
    }

    /**
     * Convert sp value to px value.
     *
     * @param context Any context.
     * @param spValue Sp value used in font-size.
     * @return The value size in px.
     */
    public static int sp2px(Context context, float spValue) {
        final float scale = getDensity(context);
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * Convert px value to sp value.
     *
     * @param context Any context.
     * @param pxValue Px value.
     * @return The value size in sp.
     */
    public static int px2sp(Context context, float pxValue) {
        final float scale = getDensity(context);
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * Convert dip(dp) value to px value.
     *
     * @param context Any context.
     * @param dipValue Dip(dp) value.
     * @return The value size in px.
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = getDensity(context);
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * Convert px value to dip(dp) value.
     *
     * @param context Any context.
     * @param pxValue Px value.
     * @return The value size in dip(dp).
     */
    public static float px2dip(Context context, float pxValue) {
        final float scale = getDensity(context);
        return (pxValue / scale + 0.5f);
    }

    private static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

}
