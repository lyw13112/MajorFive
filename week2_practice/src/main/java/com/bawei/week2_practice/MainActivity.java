package com.bawei.week2_practice;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week2_practice.adapter.LeftAdapter;
import com.bawei.week2_practice.adapter.RightAdapter;
import com.bawei.week2_practice.app.MyApp;
import com.bawei.week2_practice.base.BaseActivity;
import com.bawei.week2_practice.base.BasePresenter;
import com.bawei.week2_practice.entity.GoodsCategoryBean;
import com.bawei.week2_practice.entity.GoodsCategoryBlurbBean;
import com.bawei.week2_practice.entity.GreenDaoCacheBean;
import com.bawei.week2_practice.greendao.DaoSession;
import com.bawei.week2_practice.presenter.MyPresenter;
import com.bawei.week2_practice.util.NetUtil;
import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author 刘云蔚
 * @createTime 2020/1/3 20:43
 * @description
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.recycler_left)
    RecyclerView recyclerLeft;
    @BindView(R.id.recycler_right)
    RecyclerView recyclerRight;
    private DaoSession daoSession;

    @Override
    protected void initData() {
        if (NetUtil.getInstance().getNetState()) {
            p.getLeftData();
        } else {
            List<GreenDaoCacheBean> list = daoSession.loadAll(GreenDaoCacheBean.class);
            GreenDaoCacheBean bean = list.get(0);
            if (bean != null) {
                GoodsCategoryBean bean1 = new Gson().fromJson(bean.getJson(), GoodsCategoryBean.class);
                LeftAdapter adapter = new LeftAdapter(this, bean1.result.get(0).secondCategoryVo);
                recyclerLeft.setAdapter(adapter);
            }
        }
    }

    @Override
    protected void initView() {
        daoSession = MyApp.getDaoSession();
        recyclerLeft.setLayoutManager(new LinearLayoutManager(this));
        recyclerRight.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void viewGetData(Object o) {
        if (o instanceof GoodsCategoryBean) {
            GoodsCategoryBean bean = (GoodsCategoryBean) o;
            LeftAdapter adapter = new LeftAdapter(this, bean.result.get(0).secondCategoryVo);
            adapter.setOnItemClick(id -> {
                //发送EventBus
                EventBus.getDefault().post(id);
            });
            recyclerLeft.setAdapter(adapter);

            String json = GsonUtils.toJson(bean);
            daoSession.insertOrReplace(new GreenDaoCacheBean(json));

        } else if (o instanceof GoodsCategoryBlurbBean) {
            GoodsCategoryBlurbBean bean = (GoodsCategoryBlurbBean) o;
            RightAdapter adapter = new RightAdapter(this, bean.result);
            recyclerRight.setAdapter(adapter);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getId(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("categoryId", id);
        map.put("page", "1");
        map.put("count", "10");
        p.getRightData(map);
    }
}
