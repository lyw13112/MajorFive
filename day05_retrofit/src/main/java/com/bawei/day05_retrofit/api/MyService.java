package com.bawei.day05_retrofit.api;

import com.bawei.day05_retrofit.entity.FindCategoryBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author 刘云蔚
 * @createTime 2020/1/2 20:01
 * @description
 */
public interface MyService {

    @GET(ApiService.findCategory)
    Call<FindCategoryBean> getData();
}
