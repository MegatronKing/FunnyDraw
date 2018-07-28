package com.github.megatronking.funnydraw.sample;

import android.hardware.input.InputManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.InputEvent;
import android.view.MotionEvent;

import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.MotionDrawException;
import com.github.megatronking.funnydraw.draw.MotionDrawer;
import com.github.megatronking.funnydraw.draw.MotionDrawerCallback;
import com.github.megatronking.funnydraw.http.ServerNotSupported;
import com.github.megatronking.funnydraw.utils.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * A manager used to manage the samples.
 *
 * @author Magetron King
 * @since 18/7/27 21:57
 */
public final class SampleManager {

    private static final class Holder {

        private static final SampleManager INSTANCE = new SampleManager();

    }

    @NonNull
    public static SampleManager get() {
        return Holder.INSTANCE;
    }

    @ServerNotSupported
    private List<SampleInfo> mSamples;

    private Canvas mCanvas;

    private boolean mInIdle;

    private SampleManager() {
        mSamples = new ArrayList<>();
        mInIdle = true;
    }

    @ServerNotSupported
    public void addSamples(@NonNull List<SampleInfo> samples) {
        mSamples.addAll(samples);
    }

    @ServerNotSupported
    @NonNull
    public List<SampleInfo> list() {
        return new ArrayList<>(mSamples);
    }

    @ServerNotSupported
    @Nullable
    public String findClassByName(@NonNull String name) {
        List<SampleInfo> sampleInfos = new ArrayList<>(mSamples);
        for (SampleInfo sampleInfo : sampleInfos) {
            if (sampleInfo.name.equals(name)) {
                return sampleInfo.sampleClassName;
            }
        }
        return null;
    }

    public void setCanvas(@NonNull Canvas canvas) {
        mCanvas = canvas;
        Log.i(canvas.toString());
    }

    public void drawSample(@NonNull String className, @NonNull MotionDrawerCallback callback)
            throws MotionDrawException {
        drawSample(className, false, callback);
    }

    public void drawSampleSmoothly(@NonNull String className, @NonNull MotionDrawerCallback callback)
            throws MotionDrawException {
        drawSample(className, true, callback);
    }

    public void drawSample(@NonNull String className) throws MotionDrawException {
        drawSample(className, false, new MotionInjector());
    }

    public void drawSampleSmoothly(@NonNull String className) throws MotionDrawException {
        drawSample(className, true, new MotionInjector());
    }

    private void drawSample(String className, boolean isSmoothly, final MotionDrawerCallback callback)
            throws MotionDrawException {
        if (!mInIdle) {
            throw new MotionDrawException("A sample is in drawing, please try again later.");
        }
        Sample sample = createSampleInstance(className);
        if (sample != null) {
            MotionDrawer drawer = sample.buildDrawer(mCanvas);
            drawer.setCallback(new MotionDrawerCallback() {
                @Override
                public void onDraw(@NonNull MotionEvent event, boolean isFinished) {
                    Log.i("Motion event " + event.getAction() + " (" + event.getX() +
                            ", " + event.getY() + ")");
                    mInIdle = isFinished;
                    callback.onDraw(event, isFinished);
                }
            });
            drawer.draw(isSmoothly);
        } else {
            throw new MotionDrawException("Can't find sample : " + className);
        }
    }

    private Sample createSampleInstance(String className) {
        try {
            return (Sample) Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            return null;
        }
    }

    private class MotionInjector implements MotionDrawerCallback {

        private InputManager mInputManager;
        private Method mInjectInputEvent;

        private MotionInjector() throws MotionDrawException {
            try {
                mInputManager = (InputManager)
                        InputManager.class.getDeclaredMethod("getInstance").invoke(null);
                mInjectInputEvent =
                        InputManager.class.getDeclaredMethod("injectInputEvent",
                                InputEvent.class, Integer.TYPE);
                mInjectInputEvent.setAccessible(true);
            } catch (Exception e) {
                throw new MotionDrawException(e.getMessage());
            }
        }

        @Override
        public void onDraw(@NonNull MotionEvent event, boolean isFinished) {
            try {
                mInjectInputEvent.invoke(mInputManager, event, 0);
            } catch (Exception e) {
                Log.wtf(e);
            }
        }

    }

}
