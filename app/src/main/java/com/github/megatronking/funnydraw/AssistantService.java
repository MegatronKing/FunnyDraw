package com.github.megatronking.funnydraw;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.WebSocket;
import com.koushikdutta.async.http.callback.HttpConnectCallback;

import com.github.megatronking.funnydraw.http.RestApi;
import com.github.megatronking.funnydraw.utils.Log;

/**
 * The assistant service running in background to receive commands and handle requests.
 *
 * @author Magetron King
 * @since 18/7/19 22:44
 */

public class AssistantService extends Service {

    public static final String ACTION_DRAW =
            "com.github.megatronking.funnydraw.ACTION_DRAW";

    public static final String EXTRA_SAMPLE_NAME =
            "com.github.megatronking.funnydraw.EXTRA_SAMPLE_NAME";

    private static final int PING_INTERVAL = 10 * 1000;
    private static final String PING_MESSAGE = "I'm your father!";

    private AsyncHttpClient mHttpClient;

    private HandlerThread mHandlerThread;
    private Handler mHandler;

    private WebSocket mPingWebSocket;
    private PingResultCallback mPingResultCallback;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    public class LocalBinder extends Binder {

        public AssistantService getService() {
            return AssistantService.this;
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHttpClient = AsyncHttpClient.getDefaultInstance();

        mHandlerThread = new HandlerThread("PingThread") {

            @Override
            protected void onLooperPrepared() {
                mHandler = new Handler(getLooper());
                schedulePingRequest();
            }
        };
        mHandlerThread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandlerThread.quit();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if (ACTION_DRAW.equals(action)) {
            sendDrawRequest(intent.getStringExtra(EXTRA_SAMPLE_NAME));
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void setPingResultCallback(PingResultCallback pingCallback) {
        mPingResultCallback = pingCallback;
    }

    private void sendDrawRequest(String sampleClassName) {
        mHttpClient.execute(new AsyncHttpGet(RestApi.DRAW.getUri() + "?" + "sampleClassName="
                + sampleClassName), new HttpConnectCallback() {
            @Override
            public void onConnectCompleted(Exception ex, AsyncHttpResponse response) {
                if (ex != null) {
                    Log.e("Send draw request failed, " + ex.getMessage());
                } else {
                    if (response.code() < 200 || response.code() >= 300) {
                        Log.e("Send draw request failed, " + response.message());
                    } else {
                        Log.i("Send draw request successfully.");
                    }
                }
            }
        });
    }

    private void schedulePingRequest() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sendPingRequest();
                // schedule next ping.
                schedulePingRequest();
            }
        }, PING_INTERVAL);
    }

    private void sendPingRequest() {
        if (mPingWebSocket != null && mPingWebSocket.isOpen()) {
            mPingWebSocket.send(PING_MESSAGE);
        } else {
            mHttpClient.websocket(RestApi.PING.getUri(), RestApi.PING.path,
                    new AsyncHttpClient.WebSocketConnectCallback() {

                        @Override
                        public void onCompleted(Exception ex, final WebSocket webSocket) {
                            if (ex != null) {
                                Log.e("Ping client failed to established connection " +
                                        "with server, " + ex.getMessage());
                                if (mPingResultCallback != null) {
                                    mPingResultCallback.onResult(false);
                                }
                            } else {
                                Log.i("Ping client established connection with server.");
                                mPingWebSocket = webSocket;
                                mPingWebSocket.setStringCallback(new WebSocket.StringCallback() {
                                    @Override
                                    public void onStringAvailable(String s) {
                                        Log.i("Ping client received ping response.");
                                        if (mPingResultCallback != null) {
                                            mPingResultCallback.onResult(true);
                                        }
                                    }
                                });
                                mPingWebSocket.setClosedCallback(new CompletedCallback() {

                                    @Override
                                    public void onCompleted(Exception ex) {
                                        Log.i("Ping client web socket is closed.");
                                        if (mPingResultCallback != null) {
                                            mPingResultCallback.onResult(false);
                                        }
                                    }

                                });
                                mPingWebSocket.send(PING_MESSAGE);
                            }
                        }

                    });
        }
    }

    public interface PingResultCallback {

        void onResult(boolean isConnected);

    }

}
