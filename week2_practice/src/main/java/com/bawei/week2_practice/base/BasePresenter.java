package com.bawei.week2_practice.base;

import com.bawei.week2_practice.contract.IContract;

import java.lang.ref.WeakReference;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 9:03
 * @description
 */
public abstract class BasePresenter<V extends BaseActivity> implements IContract.IPresenter {

    protected WeakReference<V> v;

    protected void attachView(V v) {
        this.v = new WeakReference<>(v);
    }

    protected void detachView() {
        v.clear();
        v = null;
    }

    protected V getView() {
        return v.get();
    }
}
