package com.github.megatronking.funnydraw.ui;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.megatronking.funnydraw.AssistantService;
import com.github.megatronking.funnydraw.R;
import com.github.megatronking.funnydraw.model.SampleXML;
import com.github.megatronking.funnydraw.model.SamplesXML;
import com.github.megatronking.funnydraw.sample.SampleInfo;
import com.github.megatronking.funnydraw.sample.SampleManager;
import com.github.megatronking.funnydraw.utils.Log;
import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView mServerStateImage;
    private TextView mServerStateText;

    private FloatWindow mFloatWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mServerStateImage = findViewById(R.id.server_state_icon);
        mServerStateText = findViewById(R.id.server_state_text);

        findViewById(R.id.debug).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasWindowOverlayPermission()) {
                    overlayFloatWindow();
                    mFloatWindow.setDebug(true);
                    startActivity(new Intent(getApplicationContext(), DebugActivity.class));
                } else {
                    showWindowOverlayRequestDialog();
                }
            }
        });

        findViewById(R.id.run).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasWindowOverlayPermission()) {
                    overlayFloatWindow();
                    // jump to wechat app
                    mFloatWindow.setDebug(false);
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI"));
                    startActivity(intent);
                } else {
                    showWindowOverlayRequestDialog();
                }
            }
        });

        // Parse samples.xml
        List<SampleInfo> sampleInfos = parseSamples();
        if (sampleInfos != null) {
            SampleManager.get().addSamples(sampleInfos);
        }

        mFloatWindow = new FloatWindow(this);

        Intent intent = new Intent(this, AssistantService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                AssistantService assistantService = ((AssistantService.LocalBinder)service).getService();
                assistantService.setPingResultCallback(new AssistantService.PingResultCallback() {

                    @Override
                    public void onResult(boolean isConnected) {
                        if (isConnected) {
                            updateServerState2Connected();
                        } else {
                            updateServerState2Disconnected();
                        }
                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }

    private List<SampleInfo> parseSamples() {
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.processAnnotations(SamplesXML.class);
        xStream.processAnnotations(SampleXML.class);
        xStream.aliasSystemAttribute(null, "class");
        xStream.aliasSystemAttribute(null, "package");

        try {
            InputStream is = getAssets().open("samples.xml");
            SamplesXML samplesXML = (SamplesXML) xStream.fromXML(is);
            if (samplesXML == null || TextUtils.isEmpty(samplesXML.packageName)
                    || samplesXML.samples == null) {
                Log.e("Invalid samples.xml file.");
                return null;
            } else {
                Log.i("Get " + samplesXML.samples.size() + " samples from samples.xml.");
            }
            List<SampleInfo> sampleInfos = new ArrayList<>(samplesXML.samples.size());
            for(SampleXML sampleXML : samplesXML.samples) {
                if (TextUtils.isEmpty(sampleXML.className)) {
                    Log.w("Invalid sample node, missing class attribute.");
                    continue;
                }
                if (TextUtils.isEmpty(sampleXML.name)) {
                    Log.w("Invalid sample node, missing name attribute.");
                    continue;
                }
                if (sampleXML.className.startsWith(".")) {
                    sampleXML.className = samplesXML.packageName + sampleXML.className;
                }
                sampleInfos.add(new SampleInfo(sampleXML.name, sampleXML.className));
            }
            return sampleInfos;
        } catch (IOException e) {
            Log.wtf(e);
        }
        return null;
    }

    private boolean hasWindowOverlayPermission() {
        return Build.VERSION.SDK_INT < 23 || Settings.canDrawOverlays(MainActivity.this);
    }

    private void showWindowOverlayRequestDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage(R.string.window_overlay_dialog_message)
                .setPositiveButton(R.string.window_overlay_dialog_positive_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Grant overlay permission.
                        if (Build.VERSION.SDK_INT >= 23) {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            startActivity(intent);
                        }
                    }
                }).create();
        dialog.show();
    }

    private void overlayFloatWindow() {
        mFloatWindow.show();
    }

    private void updateServerState2Connected() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mServerStateImage.setImageResource(R.drawable.connected);
                mServerStateText.setText(R.string.server_connected);
            }
        });
    }

    private void updateServerState2Disconnected() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mServerStateImage.setImageResource(R.drawable.disconnect);
                mServerStateText.setText(R.string.server_disconnected);
            }
        });
    }

}
