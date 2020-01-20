package com.example.basehttp.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Description: 自定义返回类型，用来规避回传错误解析失败的情况
 * author: Luck-s
 * date: 2020/1/20
 * version: V1.0
 */

public class GsonAdapter {

    public static Gson buildGson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapter(long.class, new LongDefault0Adapter())
                .create();

        return gson;
    }
}
