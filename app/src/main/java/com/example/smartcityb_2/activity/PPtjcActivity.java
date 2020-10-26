package com.example.smartcityb_2.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.adapter.PLAdapter;
import com.example.smartcityb_2.adapter.PtjcAdapter;
import com.example.smartcityb_2.bean.PtjcBean;
import com.example.smartcityb_2.util.MyGirdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 16:50
 */
public class PPtjcActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private MyGirdView enverimentGird;
    private MyGirdView bodyGird;

    List<String> strings, strings1;

    List<PtjcBean> ptjcBeans, ptjcBeans1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ptjc_layout);

        initView();
        title.setText("平台监测");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        strings = new ArrayList<>();
        strings1 = new ArrayList<>();
        ptjcBeans = new ArrayList<>();
        ptjcBeans1 = new ArrayList<>();
        strings.add("温度");
        strings.add("湿度");
        strings.add("PM2.5");
        strings.add("CO2");
        strings1.add("心率");
        strings1.add("血压");
        strings1.add("体温");
        strings1.add("运动量");
        strings1.add("运动时间");
        strings1.add("体重");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isLoop) {
                    ptjcBeans.clear();
                    ptjcBeans1.clear();
                    int value = 0;
                    for (int i = 0; i < strings.size(); i++) {
                        switch (i) {
                            case 0:
                                value = 40;
                                break;
                            case 1:
                                value = 25;
                                break;
                            case 2:
                                value = 300;
                                break;
                            case 3:
                                value = 1000;
                                break;
                        }
                        ptjcBeans.add(new PtjcBean(strings.get(i), getColor(), random.nextInt(value) + 1));
                    }
                    for (int i = 0; i < strings1.size(); i++) {
                        switch (i) {
                            case 0:
                                ptjcBeans1.add(new PtjcBean(strings1.get(i)
                                        , getColor(), random.nextInt(100) + 1));
                                break;
                            case 1:
                                ptjcBeans1.add(new PtjcBean(strings1.get(i)
                                        , getColor(), random.nextInt(200) + 1));
                                break;
                            case 2:
                                ptjcBeans1.add(new PtjcBean(strings1.get(i)
                                        , getColor(), random.nextInt(37)));
                                break;
                            case 3:
                                ptjcBeans1.add(new PtjcBean(strings1.get(i)
                                        , getColor(), random.nextInt(2000)));
                                break;
                            case 4:
                                ptjcBeans1.add(new PtjcBean(strings1.get(i)
                                        , getColor(), random.nextInt(3) + 1));
                                break;
                            case 5:
                                ptjcBeans1.add(new PtjcBean(strings1.get(i)
                                        , getColor(), 152));
                                break;
                        }
                        handler.sendEmptyMessage(0);
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).

                start();
    }

    Random random = new Random();

    private int getColor() {
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isLoop = false;
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            enverimentGird.setAdapter(new PtjcAdapter(PPtjcActivity.this, ptjcBeans));
            bodyGird.setAdapter(new PtjcAdapter(PPtjcActivity.this, ptjcBeans1));
            return false;
        }
    });

    private boolean isLoop = true;

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        enverimentGird = findViewById(R.id.enveriment_gird);
        bodyGird = findViewById(R.id.body_gird);
    }
}
