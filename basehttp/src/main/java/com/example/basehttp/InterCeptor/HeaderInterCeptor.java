package com.example.basehttp.InterCeptor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create BY Luck-S ON 10:51
 * Email: fine9987@163.com
 * Package:com.example.basehttp.InterCeptor
 * Description:
 */
public abstract class HeaderInterCeptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Map<String, String> headers = buildHeaders();
        if (headers == null || headers.isEmpty()) {
            return chain.proceed(request);
        } else {
            Response response = chain.proceed(request.newBuilder()
                    .headers(buildHeaders(request, headers))
                    .build());
            return response;
        }
    }

    private Headers buildHeaders(Request request, Map<String, String> headerMap) {
        Headers headers = request.headers();
        if (headers != null) {
            Headers.Builder builder = headers.newBuilder();
            for (String key : headerMap.keySet()) {
                builder.add(key, headerMap.get(key));
            }
            return builder.build();
        } else {
            return headers;
        }
    }

    public abstract Map<String, String> buildHeaders();
}
