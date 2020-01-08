package com.bawei.week1_practice.presenter;

import com.bawei.week1_practice.base.BaseActivityPresenter;
import com.bawei.week1_practice.contract.IContract;
import com.bawei.week1_practice.model.MyModel;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 9:14
 * @description
 */
public class MyActivityPresenter extends BaseActivityPresenter {
    @Override
    public void getData(String url) {
        new MyModel().getData(url, new IContract.ModelCallback() {
            @Override
            public void modelSuccess(String json) {
                getView().viewGetData(json);
            }

            @Override
            public void modelError(String error) {
            }
        });
    }
}
