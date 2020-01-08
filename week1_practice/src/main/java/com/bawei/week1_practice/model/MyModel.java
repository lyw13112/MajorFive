package com.bawei.week1_practice.model;

import com.bawei.week1_practice.contract.IContract;
import com.bawei.week1_practice.util.OkHttpUtil;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 8:51
 * @description
 */
public class MyModel implements IContract.IModel {

    @Override
    public void getData(String url, final IContract.ModelCallback callback) {
        OkHttpUtil.getInstance().getData(url, new OkHttpUtil.OkCallback() {
            @Override
            public void okSuccess(String json) {
                callback.modelSuccess(json);
            }

            @Override
            public void okError(String error) {
                callback.modelError(error);
            }
        });
    }
}
