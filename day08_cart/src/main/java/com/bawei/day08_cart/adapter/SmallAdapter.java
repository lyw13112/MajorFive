package com.bawei.day08_cart.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.day08_cart.MainActivity;
import com.bawei.day08_cart.R;
import com.bawei.day08_cart.entity.DataBean;
import com.bawei.day08_cart.widget.AdderView;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

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
    private final MainActivity mainActivity;

    public SmallAdapter(Context context, List<DataBean.ResultBean.ShoppingCartListBean> list, int position) {
        this.context = context;
        this.list = list;
        this.mPosition = position;
        mainActivity = (MainActivity) context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(context, R.layout.item_small, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.check_item.setChecked(list.get(position).isChecked());
        holder.text_name.setText(list.get(position).getCommodityName());
        holder.text_price.setText(list.get(position).getPrice() + "");
        Glide.with(context).load(list.get(position).getPic()).into(holder.imageView);

        //条目多选框监听
        holder.check_item.setOnClickListener(v -> {
            //发送是否商家全选
            list.get(position).setChecked(holder.check_item.isChecked());
            callback.back(mPosition, isAllCheck());
            //发送是否全选
            mainActivity.isAllChecked();
        });

        //加减器监听
        holder.adderView.setNum(list.get(position).getNum());
        holder.adderView.setOnClickListener((AdderView.AdderOnClickListener) num -> {
            list.get(position).setNum(num);
            notifyDataSetChanged();
            mainActivity.countPrice();
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
        @BindView(R.id.adderView)
        AdderView adderView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
