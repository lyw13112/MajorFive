package com.bawei.day01_zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author 刘云蔚
 * @createTime 2019/12/26 19:44
 * @description Zxing二维码扫描
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.btn_generate)
    Button btnGenerate;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.btn_photos)
    Button btnPhotos;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        CodeUtils.init(this);
    }

    @OnClick({R.id.btn_start, R.id.btn_generate, R.id.btn_photos})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_generate:
                String s = editText.getText().toString();
                if (s.equals("")) {
                    Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
                } else {
                    Bitmap bitmap = CodeUtils.createImage(s, 300, 300, null);
                    imageView.setImageBitmap(bitmap);
                }
                break;
            case R.id.btn_start:
                CodeUtils.analyzeByCamera(this);
                break;
            case R.id.btn_photos:
                CodeUtils.analyzeByPhotos(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CodeUtils.onActivityResult(this, requestCode, resultCode, data, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                textView.setText(result);
            }

            @Override
            public void onAnalyzeFailed() {
                Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) bind.unbind();
    }
}
