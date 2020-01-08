package com.bawei.week1_practice.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week1_practice.R;
import com.bawei.week1_practice.ShowActivity;
import com.bawei.week1_practice.entity.Bean;
import com.bumptech.glide.Glide;

import java.security.PublicKey;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 15:22
 * @description
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {

    private Context context;
    private List<Bean.ResultBean> list;

    public MyAdapter(Context context, List<Bean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(context, R.layout.item_recycler, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView.setText(list.get(position).getCommodityName());
        Glide.with(context).load(list.get(position).getMasterPic()).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> context.startActivity(new Intent(context, ShowActivity.class)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.textView)
        TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
