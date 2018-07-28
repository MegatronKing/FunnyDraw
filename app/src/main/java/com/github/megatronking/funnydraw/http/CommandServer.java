package com.github.megatronking.funnydraw.http;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.github.megatronking.funnydraw.draw.MotionDrawException;
import com.github.megatronking.funnydraw.sample.SampleManager;
import com.github.megatronking.funnydraw.utils.Log;
import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.http.WebSocket;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;

/**
 * Server to execute adb command.
 *
 * @author Magetron King
 * @since 18/7/23 19:01
 */

public final class CommandServer {

    private AsyncHttpServer mAsyncHttpServer;

    private CommandServer() {
        mAsyncHttpServer = new AsyncHttpServer();
    }

    private void listenDrawAction() {
        final Handler handler = new Handler(Looper.myLooper());
        mAsyncHttpServer.get("/" + RestApi.DRAW.path, new HttpServerRequestCallback() {

            @Override
            public void onRequest(AsyncHttpServerRequest request, final AsyncHttpServerResponse response) {
                final String sampleClassName = request.getQuery().getString("sampleClassName");
                Log.i("Server received draw request: " + sampleClassName);
                if (TextUtils.isEmpty(sampleClassName)) {
                    response.code(404);
                    response.end();
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                SampleManager.get().drawSampleSmoothly(sampleClassName);
                                response.code(204);
                                response.end();
                            } catch (MotionDrawException e) {
                                Log.wtf(e);
                                response.code(500);
                                response.end();
                            }
                        }
                    });
                }
            }

        });
        mAsyncHttpServer.listen(new AsyncServer(), RestApi.DRAW.port);
    }

    private void listenPingAction() {
        mAsyncHttpServer.websocket("/" + RestApi.PING.path, RestApi.PING.path, new AsyncHttpServer.WebSocketRequestCallback() {
            @Override
            public void onConnected(final WebSocket webSocket, AsyncHttpServerRequest request) {
                Log.i("Ping server established connection with client.");
                webSocket.setStringCallback(new WebSocket.StringCallback() {
                    @Override
                    public void onStringAvailable(String s) {
                        Log.i("Ping server received ping request.");
                        webSocket.send("No! No! That's not true! That's impossible!");
                    }
                });
            }

        });
        mAsyncHttpServer.listen(new AsyncServer(), RestApi.PING.port);
    }

    public static void start() {
        Log.i("Command server is starting.");
        CommandServer server = new CommandServer();
        server.listenDrawAction();
        server.listenPingAction();
        Log.i("Command server started now.");
    }

}
