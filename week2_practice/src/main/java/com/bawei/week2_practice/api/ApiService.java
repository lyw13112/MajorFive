package com.bawei.week2_practice.api;

import com.bawei.week2_practice.entity.GoodsCategoryBean;
import com.bawei.week2_practice.entity.GoodsCategoryBlurbBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author 刘云蔚
 * @createTime 2020/1/3 19:07
 * @description
 */
public interface ApiService {

    @GET(MyApi.findCategory)
    Observable<GoodsCategoryBean> getCategory();

    @GET(MyApi.findCommodityByCategory)
    Observable<GoodsCategoryBlurbBean> getCommodityByCategory(@QueryMap Map<String, String> map);
}
