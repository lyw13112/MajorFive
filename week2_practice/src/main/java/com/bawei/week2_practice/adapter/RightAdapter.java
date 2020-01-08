package com.bawei.week2_practice.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week2_practice.R;
import com.bawei.week2_practice.entity.GoodsCategoryBean;
import com.bawei.week2_practice.entity.GoodsCategoryBlurbBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author 刘云蔚
 * @createTime 2020/1/3 20:46
 * @description
 */
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.Holder> {

    private Context context;
    private List<GoodsCategoryBlurbBean.ResultBean> list;

    public RightAdapter(Context context, List<GoodsCategoryBlurbBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(context, R.layout.item_right, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView.setText(list.get(position).commodityName);
        Glide.with(context).load(list.get(position).masterPic).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
