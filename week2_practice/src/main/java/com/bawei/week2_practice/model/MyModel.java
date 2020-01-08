package com.bawei.week2_practice.model;

import com.bawei.week2_practice.api.ApiService;
import com.bawei.week2_practice.contract.IContract;
import com.bawei.week2_practice.entity.GoodsCategoryBean;
import com.bawei.week2_practice.entity.GoodsCategoryBlurbBean;
import com.bawei.week2_practice.util.NetUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 8:51
 * @description
 */
public class MyModel implements IContract.IModel {

    @Override
    public void getGoodsCategory(final IContract.ModelCallback callback) {
        NetUtil.getInstance().createClass(ApiService.class).getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodsCategoryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GoodsCategoryBean goodsCategoryBean) {
                        callback.modelSuccess(goodsCategoryBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.modelError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getGoodsCategoryBlurb(Map<String, String> map, final IContract.ModelCallback callback) {
        NetUtil.getInstance().createClass(ApiService.class).getCommodityByCategory(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodsCategoryBlurbBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GoodsCategoryBlurbBean goodsCategoryBlurbBean) {
                        callback.modelSuccess(goodsCategoryBlurbBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.modelError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
