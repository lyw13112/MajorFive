package com.bawei.week2_practice.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.week2_practice.contract.IContract;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 9:03
 * @description
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IContract.IView {
    protected P p;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        //注册EventBus
        EventBus.getDefault().register(this);
        //绑定ButterKnife
        bind = ButterKnife.bind(this);
        p = initPresenter();
        if (p != null) p.attachView(this);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract int initLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (p != null) {
            p.detachView();
            p = null;
        }
        if (bind != null) {
            bind.unbind();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
