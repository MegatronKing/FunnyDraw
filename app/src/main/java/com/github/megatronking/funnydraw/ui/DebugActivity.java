package com.github.megatronking.funnydraw.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.github.megatronking.funnydraw.R;
import com.github.megatronking.funnydraw.draw.Canvas;
import com.github.megatronking.funnydraw.draw.MotionDrawException;
import com.github.megatronking.funnydraw.draw.MotionDrawerCallback;
import com.github.megatronking.funnydraw.sample.SampleManager;
import com.github.megatronking.funnydraw.utils.Log;

/**
 * Debug page.
 *
 * @author Magetron King
 * @since 18/7/28 10:13
 */

public class DebugActivity extends AppCompatActivity {

    public static final String ACTION_DEBUG_DRAW =
            "com.github.megatronking.funnydraw.ACTION_DEBUG_DRAW";

    public static final String EXTRA_DEBUG_NAME =
            "com.github.megatronking.funnydraw.EXTRA_DEBUG_NAME";

    private DebugCanvasView mCanvasView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        mCanvasView = findViewById(R.id.debug_canvas_view);
        mCanvasView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                SampleManager.get().setCanvas(new Canvas(mCanvasView.getWidth(),
                        mCanvasView.getHeight(), (mCanvasView.getLeft() + mCanvasView.getRight()) / 2,
                        (mCanvasView.getTop() + mCanvasView.getBottom()) / 2));
                mCanvasView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        final ImageView canvasEraser = findViewById(R.id.debug_canvas_eraser);
        canvasEraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCanvasView.clearCanvas();
            }
        });


        IntentFilter filter = new IntentFilter(ACTION_DEBUG_DRAW);
        registerReceiver(mDebugDrawReceiver ,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mDebugDrawReceiver);
    }

    private BroadcastReceiver mDebugDrawReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String sampleClassName = intent.getStringExtra(EXTRA_DEBUG_NAME);
            if (sampleClassName != null) {
                try {
                    SampleManager.get().drawSampleSmoothly(sampleClassName, new MotionDrawerCallback() {
                        @Override
                        public void onDraw(@NonNull MotionEvent event, boolean isFinished) {
                            mCanvasView.drawMotionEventOnCanvas(event);
                        }
                    });
                } catch (MotionDrawException e) {
                    Log.e("Draw debug sample failed, " + e.getMessage());
                }
            }
        }
    };

}
