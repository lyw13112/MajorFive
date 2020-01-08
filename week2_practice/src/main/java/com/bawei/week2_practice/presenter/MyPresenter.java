package com.bawei.week2_practice.presenter;

import com.bawei.week2_practice.base.BasePresenter;
import com.bawei.week2_practice.contract.IContract;
import com.bawei.week2_practice.model.MyModel;

import java.util.Map;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 9:14
 * @description
 */
public class MyPresenter extends BasePresenter {

    MyModel model = new MyModel();

    @Override
    public void getLeftData() {
        model.getGoodsCategory(new IContract.ModelCallback() {
            @Override
            public void modelSuccess(Object o) {
                getView().viewGetData(o);
            }

            @Override
            public void modelError(String error) {

            }
        });
    }

    @Override
    public void getRightData(Map<String, String> map) {
        model.getGoodsCategoryBlurb(map, new IContract.ModelCallback() {
            @Override
            public void modelSuccess(Object o) {
                getView().viewGetData(o);
            }

            @Override
            public void modelError(String error) {

            }
        });
    }
}
