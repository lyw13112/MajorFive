package com.bawei.week2_practice.contract;

import java.util.Map;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 8:47
 * @description
 */
public interface IContract {

    interface IModel {
        void getGoodsCategory(ModelCallback callback);

        void getGoodsCategoryBlurb(Map<String, String> map, ModelCallback callback);
    }

    interface ModelCallback {
        void modelSuccess(Object o);

        void modelError(String error);
    }

    interface IView {
        void viewGetData(Object o);
    }

    interface IPresenter {
        void getLeftData();
        void getRightData(Map<String,String> map);
    }
}
