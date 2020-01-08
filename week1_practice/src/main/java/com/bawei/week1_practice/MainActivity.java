package com.bawei.week1_practice;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week1_practice.adapter.MyAdapter;
import com.bawei.week1_practice.base.BaseActivity;
import com.bawei.week1_practice.base.BaseActivityPresenter;
import com.bawei.week1_practice.entity.Bean;
import com.bawei.week1_practice.presenter.MyActivityPresenter;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 15:31
 * @description
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void initData() {
        p.getData("http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=1&page=1&count=5");
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    protected BaseActivityPresenter initPresenter() {
        return new MyActivityPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void viewGetData(String json) {
        Bean bean = new Gson().fromJson(json, Bean.class);
        MyAdapter myAdapter = new MyAdapter(this, bean.getResult());
        recyclerView.setAdapter(myAdapter);
    }
}
