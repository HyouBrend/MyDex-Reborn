package com.myor.mydex.apiclient;

import com.android.volley.toolbox.HttpHeaderParser;
import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.ByteString;

/* loaded from: classes2.dex */
public class HeaderInterceptor implements Interceptor {
    public String CLIENT_ID = "myDex2023!mobile";
    private String CLIENT_SECRET = "qw3rty1D3v5sM@yor42021M0b1l3";

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String base64 = ByteString.encodeUtf8(this.CLIENT_ID + ":" + this.CLIENT_SECRET).base64();
        return chain.proceed(request.newBuilder().headers(new Headers.Builder().add("Authorization", "Basic " + base64).add(HttpHeaderParser.HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded; charset=utf-8").build()).method(request.method(), request.body()).build());
    }
}
