package com.bawei.week2_practice.api;

/**
 * @author 刘云蔚
 * @createTime 2020/1/3 19:02
 * @description
 */
public class MyApi {
    public static final String BASE_URL = "http://172.17.8.100/small/";

    //查询商品类目
    public static final String findCategory = "commodity/v1/findCategory";

    //根据二级类目查询商品信息
    public static final String findCommodityByCategory = "commodity/v1/findCommodityByCategory";
}
