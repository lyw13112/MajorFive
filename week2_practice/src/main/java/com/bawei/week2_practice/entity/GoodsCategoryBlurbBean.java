package com.bawei.week2_practice.entity;

import java.util.List;

/**
 * @author 刘云蔚
 * @createTime 2020/1/3 19:41
 * @description
 */
public class GoodsCategoryBlurbBean {
    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        public String commodityId;
        public String commodityName;
        public String masterPic;
        public String price;
        public String saleNum;
    }
}
