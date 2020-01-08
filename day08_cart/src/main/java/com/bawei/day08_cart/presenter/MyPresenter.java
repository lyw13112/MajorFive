package com.bawei.day08_cart.presenter;

import android.util.Log;

import com.bawei.day08_cart.base.BasePresenter;
import com.bawei.day08_cart.contract.IContract;
import com.bawei.day08_cart.model.MyModel;

import java.util.Map;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 9:14
 * @description
 */
public class MyPresenter extends BasePresenter {

    @Override
    public void getData(Map<String, String> map) {
        new MyModel().modelGetData(map, new IContract.ModelCallback() {
            @Override
            public void modelSuccess(Object o) {
                getView().viewGetData(o);
            }

            @Override
            public void modelError(String error) {
                Log.i("TAG", "modelError: " + error);
            }
        });
    }
}
