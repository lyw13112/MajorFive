package com.bawei.day05_retrofit.util;

import com.bawei.day05_retrofit.api.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 刘云蔚
 * @createTime 2020/1/2 19:51
 * @description
 */
public class RetrofitUtil {

    private final Retrofit retrofit;

    private RetrofitUtil() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    private static class SingleHolder {
        private static final RetrofitUtil UTIL = new RetrofitUtil();
    }

    public static RetrofitUtil getInstance() {
        return SingleHolder.UTIL;
    }

    public <T> T createService(Class<T> tClass) {
        return retrofit.create(tClass);
    }

}
