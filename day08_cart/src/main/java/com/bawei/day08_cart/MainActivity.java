package com.bawei.day08_cart;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bawei.day08_cart.adapter.BigAdapter;
import com.bawei.day08_cart.adapter.SmallAdapter;
import com.bawei.day08_cart.base.BaseActivity;
import com.bawei.day08_cart.base.BasePresenter;
import com.bawei.day08_cart.entity.DataBean;
import com.bawei.day08_cart.presenter.MyPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.xRecycler)
    XRecyclerView xRecycler;
    @BindView(R.id.check_all)
    CheckBox checkAll;
    @BindView(R.id.text_all_price)
    TextView textAllPrice;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.linear_bottom)
    LinearLayout linearBottom;
    private DataBean bean;
    private BigAdapter adapter;

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("userId", "11758");
        map.put("sessionId", "157831640389211758");
        p.getData(map);
    }

    @Override
    protected void initView() {
        xRecycler.setLayoutManager(new LinearLayoutManager(this));
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
        if (o instanceof DataBean) {
            bean = (DataBean) o;
            adapter = new BigAdapter(this, bean.getResult());
            xRecycler.setAdapter(adapter);

            //全选按钮监听事件
            checkAll.setOnClickListener(v -> {
                //获取按钮状态
                boolean checked = checkAll.isChecked();
                for (DataBean.ResultBean resultBean : bean.getResult()) {
                    for (DataBean.ResultBean.ShoppingCartListBean cartList : resultBean.getShoppingCartList()) {
                        cartList.setChecked(checked);
                    }
                    resultBean.setChecked(checked);
                }
                adapter.notifyDataSetChanged();
            });
        }
    }

    //计算总价
    public void countPrice() {
        int allPrice = 0;
        for (DataBean.ResultBean resultBean : bean.getResult()) {
            for (DataBean.ResultBean.ShoppingCartListBean listBean : resultBean.getShoppingCartList()) {
                allPrice += listBean.getPrice() * listBean.getNum();
            }
        }
        textAllPrice.setText("￥" + allPrice);
    }

    //全选反选
    public void isAllChecked() {
        boolean isAllCheck = true;
        for (DataBean.ResultBean resultBean : bean.getResult()) {
            for (DataBean.ResultBean.ShoppingCartListBean cartList : resultBean.getShoppingCartList()) {
                if (!cartList.isChecked()) isAllCheck = false;
            }
            if (!resultBean.isChecked()) isAllCheck = false;
        }
        checkAll.setChecked(isAllCheck);
    }
}
