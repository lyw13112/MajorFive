package com.bawei.week2_practice.util;

import com.bawei.week2_practice.api.MyApi;
import com.blankj.utilcode.util.NetworkUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 刘云蔚
 * @createTime 2020/1/3 18:59
 * @description 网络工具类
 */
public class NetUtil {

    private final Retrofit retrofit;

    private NetUtil() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static class SingleHolder {
        private static final NetUtil UTIL = new NetUtil();
    }

    public static NetUtil getInstance() {
        return SingleHolder.UTIL;
    }

    public boolean getNetState() {
        return NetworkUtils.isConnected();
    }

    public <T> T createClass(Class<T> tClass) {
        return retrofit.create(tClass);
    }
}
