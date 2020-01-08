package com.bawei.majorfive.util;

import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author 刘云蔚
 * @createTime 2019/12/27 19:38
 * @description
 */
public class OkhttpUtil {

    private Handler handler = new Handler();
    private final OkHttpClient client;

    //单例——私有化构造方法
    private OkhttpUtil() {
        client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    private static class SingleHolder {
        private static final OkhttpUtil UTIL = new OkhttpUtil();
    }

    public static OkhttpUtil getInstance() {
        return SingleHolder.UTIL;
    }

    public void doGet(String url, final OkCallback callback) {
        Request request = new Request.Builder()
                .url(url)
                .get()
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

    public void doPost(String url, Map<String, String> map, final OkCallback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> m : map.entrySet()) {
            builder.add(m.getKey(), m.getValue());
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
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
            public void onResponse(@NotNull Call call, @NotNull final Response response) {
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
