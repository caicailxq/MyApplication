package com.bawei.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.MeituanFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
private Myadapter adapter;
    private RecyclerView rec;
    SpringView springview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         rec=findViewById(R.id.recycleview);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rec.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        springview=findViewById(R.id.springview);
        springview.setHeader(new MeituanHeader(this));
        springview.setFooter(new MeituanFooter(this));
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

               doGet();



            }

            @Override
            public void onLoadmore() {
              doGet();

            }
        });
        doGet();

    }

    private void doGet() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
           //创建一个Request
        final Request request = new Request.Builder()
                .url("http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=20&gender=2&ts=1871746850&page=1")
                .build();
         //new call
        Call call = mOkHttpClient.newCall(request);
         //请求加入调度
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        Qbean qbean = gson.fromJson(result, Qbean.class);
                        List<Qbean.DataBean> list =qbean.getData();
                        adapter=new Myadapter(list,MainActivity.this);
                        rec.setAdapter(adapter);
                        springview.onFinishFreshAndLoad();
                    }
                });


            }


        });




    }
}
