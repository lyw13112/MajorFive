package com.bawei.week1_practice.contract;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 8:47
 * @description
 */
public interface IContract {

    interface IModel {
        void getData(String url, ModelCallback callback);
    }

    interface ModelCallback {
        void modelSuccess(String json);

        void modelError(String error);
    }

    interface IView {
        void viewGetData(String json);
    }

    interface IPresenter {
        void getData(String url);
    }
}
