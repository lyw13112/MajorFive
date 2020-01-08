package com.bawei.week1_practice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bawei.week1_practice.entity.Goods;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 16:36
 * @description
 */
public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.btn_sc)
    Button btnSc;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.btn_string)
    Button btnString;
    @BindView(R.id.btn_custom)
    Button btnCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        CodeUtils.init(this);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @OnClick(R.id.btn_sc)
    public void onViewClicked() {
        String s = editText.getText().toString();
        if (!s.equals("")) {
            Bitmap image = CodeUtils.createImage(s, 300, 300, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            imageView.setImageBitmap(image);
        } else {
            Toast.makeText(this, "请输入", Toast.LENGTH_SHORT).show();
        }
    }

    @OnLongClick(R.id.imageView)
    public void lysb() {
        CodeUtils.analyzeByImageView(imageView, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Toast.makeText(ShowActivity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(ShowActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.btn_string, R.id.btn_custom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_string:
                EventBus.getDefault().post("我是字符串");
                break;
            case R.id.btn_custom:
                Goods riven = new Goods("riven", 999);
                EventBus.getDefault().post(riven);
                break;
        }
    }

    @Subscribe
    public void getString(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void getGoods(Goods g){
        Toast.makeText(this, g.getName()+","+g.getPrice()+"元", Toast.LENGTH_SHORT).show();
    }

}
