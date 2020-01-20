package com.example.basehttp.Factory;

import com.example.basehttp.Builder.RetrofitBuilder;
import com.example.basehttp.Manager.UrlManager;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Create BY Luck-S ON 11:09
 * Email: fine9987@163.com
 * Package:com.example.basehttp.Factory
 * Description: API工厂
 */
public class ApiFactory {
    private volatile static ApiFactory instance;
    private static HashMap<String, Object> apiServiceCache;
    private CallAdapter.Factory[] callAdapterFactory;
    private Converter.Factory[] converterFactory;
    private OkHttpClient okHttpClient;

    public static ApiFactory getInstance() {
        if (instance == null) {
            synchronized (ApiFactory.class) {
                if (instance == null) {
                    instance = new ApiFactory();
                }
            }
        }
        return instance;
    }

    private ApiFactory() {
        apiServiceCache = new HashMap<>();
    }

    public void clearAppApi() {
        apiServiceCache.clear();
    }

    public ApiFactory setCallAdapterFactory(CallAdapter.Factory... callAdapterFactory) {
        this.callAdapterFactory = callAdapterFactory;
        return this;
    }

    public ApiFactory setConverterFactory(Converter.Factory... converterFactory) {
        this.converterFactory = converterFactory;
        return this;
    }

    public ApiFactory setOkhttpClient(OkHttpClient okhttpClient) {
        this.okHttpClient = okhttpClient;
        return this;
    }

    public ApiFactory setBaseUrl(String baseUrl) {
        UrlManager.getInstance().setUrl(baseUrl);
        return this;
    }

    public <A> A createApi(Class<A> apiClass) {
        String urlKey = UrlManager.DEFAULT_URL_KEY;
        String urlValue = UrlManager.getInstance().getUrl();
        return createApi(urlKey, urlValue, apiClass);
    }

    public <A> A createApi(String baseUrlKey, String baseUrlValue, Class<A> apiClass) {
        String apiKey = getApiKey(baseUrlKey, apiClass);
        A api = (A) apiServiceCache.get(apiKey);
        if (api == null) {
            Retrofit retrofit = new RetrofitBuilder()
                    .setBaseUrl(baseUrlValue)
                    .setCallAdapterFactory(callAdapterFactory)
                    .setConverterFactory(converterFactory)
                    .setOkHttpClient(okHttpClient)
                    .build();
            api = retrofit.create(apiClass);
            apiServiceCache.put(apiKey, api);
        }
        return api;
    }

    private static <A> String getApiKey(String baseUrlKey, Class<A> apiClass) {
        return String.format("%s_%s", baseUrlKey, apiClass);
    }


}
