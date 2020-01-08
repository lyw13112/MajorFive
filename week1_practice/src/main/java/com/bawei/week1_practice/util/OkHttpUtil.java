package com.bawei.week1_practice.util;

import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 8:52
 * @description OkHttp单例请求网络数据
 */
public class OkHttpUtil {

    private Handler handler = new Handler();
    private final OkHttpClient client;

    private OkHttpUtil() {
        client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    private static class SingleHolder {
        private static final OkHttpUtil UTIL = new OkHttpUtil();
    }

    public static OkHttpUtil getInstance() {
        return SingleHolder.UTIL;
    }

    public void getData(String url, final OkCallback callback) {

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.okError(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            callback.okSuccess(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public interface OkCallback {
        void okSuccess(String json);

        void okError(String error);
    }
}
