package com.bawei.day08_cart.api;

import com.bawei.day08_cart.entity.DataBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

/**
 * @author 刘云蔚
 * @createTime 2020/1/3 19:07
 * @description
 */
public interface ApiService {

    @GET(MyApi.findCategory)
    Observable<DataBean> getData(@HeaderMap Map<String, String> map);
}
