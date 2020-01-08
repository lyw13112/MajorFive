package com.bawei.day02_eventbus;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_start)
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        EventBus.getDefault().postSticky("best riven YW");
        startActivity(new Intent(this, Main2Activity.class));
    }

    @Subscribe
    public void getName(String name) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

}
