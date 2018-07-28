package com.github.megatronking.funnydraw.http;

import android.support.annotation.NonNull;

/**
 * Define all rest apis.
 *
 * @author Magetron King
 * @since 18/7/27 19:07
 */

public enum RestApi {

    DRAW(55155, "draw"),

    PING(55156, "ping");

    private static final String BASE_URI = "http://localhost:";

    public final int port;
    public final String path;

    RestApi(int port, String path) {
        this.port = port;
        this.path = path;
    }

    @NonNull
    public String getUri() {
        return BASE_URI + port + "/" + path;
    }

}
