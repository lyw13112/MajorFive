package com.bawei.week2_practice.entity;

import java.util.List;

/**
 * @author 刘云蔚
 * @createTime 2020/1/3 19:29
 * @description
 */
public class GoodsCategoryBean {
    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        public String id;
        public String name;
        public List<SecondCategoryVoBean> secondCategoryVo;

        public static class SecondCategoryVoBean {
            public String id;
            public String name;
        }
    }
}
