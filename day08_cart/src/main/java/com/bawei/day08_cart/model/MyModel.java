package com.bawei.day08_cart.model;

import com.bawei.day08_cart.api.ApiService;
import com.bawei.day08_cart.contract.IContract;
import com.bawei.day08_cart.entity.DataBean;
import com.bawei.day08_cart.util.NetUtil;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 8:51
 * @description
 */
public class MyModel implements IContract.IModel {

    @Override
    public void modelGetData(Map<String, String> map, IContract.ModelCallback callback) {
        NetUtil.getInstance().createClass(ApiService.class).getData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DataBean>() {
                    @Override
                    public void accept(DataBean dataBean) throws Exception {
                        callback.modelSuccess(dataBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.modelError(throwable.getMessage());
                    }
                });
    }
}
