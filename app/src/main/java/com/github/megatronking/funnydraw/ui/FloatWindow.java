package com.github.megatronking.funnydraw.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.megatronking.funnydraw.AssistantService;
import com.github.megatronking.funnydraw.R;
import com.github.megatronking.funnydraw.sample.SampleInfo;
import com.github.megatronking.funnydraw.sample.SampleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A float window overlay wechat app.
 *
 * @author Magetron King
 * @since 18/7/19 20:30
 */

public final class FloatWindow {

    private final Context mContext;
    private final WindowManager mWindowManager;

    private boolean mShowing;
    private boolean mDebugging;

    public FloatWindow(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public void setDebug(boolean debug) {
        mDebugging = debug;
    }

    public void show() {
        if (mShowing) {
            return;
        }
        mShowing = true;

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            params.type =  WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        params.format = PixelFormat.RGBA_8888;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.gravity = Gravity.END | Gravity.TOP;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;

        View view = View.inflate(mContext, R.layout.float_window_layout, null);

        final List<SampleInfo> sampleInfos = SampleManager.get().list();
        final ListView listView = view.findViewById(R.id.sample_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setVisibility(View.GONE);
                if (mDebugging) {
                    Intent intent = new Intent(DebugActivity.ACTION_DEBUG_DRAW);
                    intent.putExtra(DebugActivity.EXTRA_DEBUG_NAME, sampleInfos.get(position).sampleClassName);
                    mContext.sendBroadcast(intent);
                } else {
                    Intent intent = new Intent(mContext, AssistantService.class);
                    intent.setAction(AssistantService.ACTION_DRAW);
                    intent.putExtra(AssistantService.EXTRA_SAMPLE_NAME, sampleInfos.get(position).sampleClassName);
                    mContext.startService(intent);
                }
            }
        });
        List<String> sampleNames = new ArrayList<>(sampleInfos.size());
        for (SampleInfo info : sampleInfos) {
            sampleNames.add(info.name);
        }
        listView.setAdapter(new ArrayAdapter<>(mContext, R.layout.item_sample_list, sampleNames));

        view.findViewById(R.id.sample_list_switcher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(listView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        mWindowManager.addView(view, params);
    }

}
