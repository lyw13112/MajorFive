package com.bawei.day08_cart.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.day08_cart.R;
import com.bawei.day08_cart.entity.DataBean;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 刘云蔚
 * @createTime 2020/1/3 20:46
 * @description
 */
public class SmallAdapter extends RecyclerView.Adapter<SmallAdapter.Holder> {

    private Context context;
    private List<DataBean.ResultBean.ShoppingCartListBean> list;
    private int mPosition;
    List<DataBean.ResultBean> bigBean;

    public SmallAdapter(Context context, List<DataBean.ResultBean.ShoppingCartListBean> list, int position, List<DataBean.ResultBean> bigBean) {
        this.context = context;
        this.list = list;
        this.mPosition = position;
        this.bigBean = bigBean;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(context, R.layout.item_small, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.text_name.setText(list.get(position).getCommodityName());
        holder.text_price.setText(list.get(position).getPrice() + "");
        Glide.with(context).load(list.get(position).getPic()).into(holder.imageView);

        holder.check_item.setChecked(list.get(position).isChecked());
        holder.check_item.setOnClickListener(v -> {
            list.get(position).setChecked(holder.check_item.isChecked());
            callback.back(mPosition, isAllCheck());

            boolean isAllCheck = true;
            for (DataBean.ResultBean resultBean : bigBean) {
                for (DataBean.ResultBean.ShoppingCartListBean cartList: resultBean.getShoppingCartList()) {
                    if (!cartList.isChecked()) isAllCheck = false;
                }
                if (!resultBean.isChecked()) isAllCheck = false;
            }
            EventBus.getDefault().postSticky(isAllCheck);

//            Log.i("TAG", "onBindViewHolder: "+bigAllChecked);
//            if (bigAllChecked!=null){
//                bigAllChecked.back(isAllCheck);
//            }

        });
    }

    public boolean isAllCheck() {
        boolean isAllCheck = true;
        for (DataBean.ResultBean.ShoppingCartListBean bean : list) {
            if (!bean.isChecked()) {
                isAllCheck = false;
            }
        }
        return isAllCheck;
    }

    SmallCallback callback;

    public interface SmallCallback {
        void back(int position, boolean isChecked);
    }

    public void setCallback(SmallCallback callback) {
        this.callback = callback;
    }


    BigAllChecked bigAllChecked;

    public interface BigAllChecked {
        void back(boolean isChecked);
    }

    public void setBigAllChecked(BigAllChecked bigAllChecked) {
        this.bigAllChecked = bigAllChecked;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.text_name)
        TextView text_name;
        @BindView(R.id.text_price)
        TextView text_price;
        @BindView(R.id.check_item)
        CheckBox check_item;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
