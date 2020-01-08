package com.bawei.day02_eventbus;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.btn_back)
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true)
    public void getName(String name) {
        textView.setText(name);
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        EventBus.getDefault().post("哈哈哈");
        finish();
    }
}
