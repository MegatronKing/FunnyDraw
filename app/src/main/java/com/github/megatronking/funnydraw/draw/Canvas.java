package com.github.megatronking.funnydraw.draw;

/**
 * The canvas that the motion event can draw.
 *
 * @author Magetron King
 * @since 18/7/27 11:33
 */

public final class Canvas {

    /**
     * The width of the google ai drawing layer.
     */
    public final int width;

    /**
     * The height of the google ai drawing layer.
     */
    public final int height;

    /**
     * The x coordinate of the drawing layer center point.
     */
    public final int centerX;

    /**
     * The y coordinate of the drawing layer center point.
     */
    public final int centerY;

    /**
     * The x coordinate of the drawing layer's left.
     */
    public final int left;

    /**
     * The y coordinate of the drawing layer's top.
     */
    public final int top;

    /**
     * The x coordinate of the drawing layer's right.
     */
    public final int right;

    /**
     * The y coordinate of the drawing layer's bottom.
     */
    public final int bottom;

    public Canvas(int width, int height, int centerX, int centerY) {
        this.width = width;
        this.height = height;
        this.centerX = centerX;
        this.centerY = centerY;

        this.left = centerX - width / 2;
        this.top = centerY - height / 2;
        this.right = centerX + width / 2;
        this.bottom = centerY + height / 2;
    }

    @Override
    public String toString() {
        return "Canvas{" +
                "width=" + width +
                ", height=" + height +
                ", centerX=" + centerX +
                ", centerY=" + centerY +
                ", left=" + left +
                ", top=" + top +
                ", right=" + right +
                ", bottom=" + bottom +
                '}';
    }
}
