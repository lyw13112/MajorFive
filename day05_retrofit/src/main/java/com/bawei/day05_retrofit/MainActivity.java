package com.bawei.day05_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.bawei.day05_retrofit.api.MyService;
import com.bawei.day05_retrofit.entity.FindCategoryBean;
import com.bawei.day05_retrofit.util.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitUtil.getInstance().createService(MyService.class).getData().enqueue(new Callback<FindCategoryBean>() {
            @Override
            public void onResponse(Call<FindCategoryBean> call, Response<FindCategoryBean> response) {
                Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<FindCategoryBean> call, Throwable t) {

            }
        });
    }
}
