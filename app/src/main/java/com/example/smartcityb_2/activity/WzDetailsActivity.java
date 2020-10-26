package com.example.smartcityb_2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.adapter.ClazAdapter;
import com.example.smartcityb_2.bean.Clwz;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 11:51
 */
public class WzDetailsActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;
    private Button btMore;

    public static void newInstance(String cp, Context context) {
        Intent intent = new Intent(context, WzDetailsActivity.class);
        intent.putExtra("info", cp);
        context.startActivity(intent);
    }

    private String cp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wz_details);
        initView();
        cp = getIntent().getStringExtra("info");
        title.setText("查询结果");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setVolley();
        btMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setMyCount(clwzs.size());
                adapter.notifyDataSetChanged();
                Util.showToast("加载成功", WzDetailsActivity.this);
            }
        });

    }


    List<Clwz> clwzs;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getViolationsByCarId")
                .setJsonObject("carid", cp)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        clwzs = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<Clwz>>() {
                                }.getType());
                        setListView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    Random random = new Random();
    ClazAdapter adapter;

    private void setListView() {
        int count = 5 + random.nextInt(1);
        if (count > clwzs.size()) {
            count = clwzs.size();
        }
        if (count == 0) {
            Util.showDialog(cp + "车辆暂无违章记录", this);
        }
        adapter = new ClazAdapter(this, clwzs, count);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WzDetailsLook.newInstance(clwzs.get(position), WzDetailsActivity.this);
            }
        });


    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        listView = findViewById(R.id.list_view);
        btMore = findViewById(R.id.bt_more);
    }
}
