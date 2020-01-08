package com.bawei.majorfive;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bawei.majorfive.api.Api;
import com.bawei.majorfive.util.OkhttpUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 刘云蔚
 * @createTime 2019/12/27 19:23
 * @description
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        OkhttpUtil.getInstance().doGet(Api.HOME_GOODS, new OkhttpUtil.OkCallback() {
            @Override
            public void okSuccess(String json) {
                Toast.makeText(MainActivity.this, json, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void okError(String error) {

            }
        });
    }
}
