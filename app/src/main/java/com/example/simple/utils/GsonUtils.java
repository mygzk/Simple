package com.example.simple.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by guozhk on 16-8-4.
 */
public class GsonUtils {
    public static <T> T fromJson(String json, TypeToken<T> token) {
        if (json == null || "".equals(json) || "{}".equals(json) || "[]".equals(json)) {
            return null;
        }
        Gson gson = new Gson();
        try {
            return gson.fromJson(json, token.getType());
        } catch (Exception ex) {
            Log.e(json + " 无法转换为 " + token.getRawType().getName() + " 对象!", ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }


    public static <T> String objectToJsonStr(T t) {
        if (t == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(t);

    }


    // 将Json数据解析成相应的映射对象
    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }

    // 将Json数组解析成相应的映射对象列表
    public static <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {
        }.getType());
        return result;
    }
}
