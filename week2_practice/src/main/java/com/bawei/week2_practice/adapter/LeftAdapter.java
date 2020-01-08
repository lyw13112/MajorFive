package com.bawei.week2_practice.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week2_practice.R;
import com.bawei.week2_practice.entity.GoodsCategoryBean;

import java.util.List;

/**
 * @author 刘云蔚
 * @createTime 2020/1/3 20:46
 * @description
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.Holder> {

    private Context context;
    private List<GoodsCategoryBean.ResultBean.SecondCategoryVoBean> list;

    public LeftAdapter(Context context, List<GoodsCategoryBean.ResultBean.SecondCategoryVoBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(context, R.layout.item_left, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView.setText(list.get(position).name);
        if (onItemClick!=null)
        holder.itemView.setOnClickListener(v -> onItemClick.onClick(list.get(position).id));
    }

    onItemClick onItemClick;

    public interface onItemClick {
        void onClick(String id);
    }

    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
