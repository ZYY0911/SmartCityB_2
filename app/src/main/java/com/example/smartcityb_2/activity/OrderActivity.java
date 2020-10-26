package com.example.smartcityb_2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.adapter.OrderTitleAdapter;
import com.example.smartcityb_2.bean.OrderTitle;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 9:41
 */
public class OrderActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout);
        initView();
        title.setText("我的订单");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setVolley();
    }


    List<String> strings;

    private void setVolley() {
        map = new HashMap<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllOrderType")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        strings = new ArrayList<>();
                        JSONArray jsonArray = jsonObject.optJSONArray(Util.Row);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            strings.add(jsonArray.optString(i));
                        }
                        for (int i = 0; i < strings.size(); i++) {
                            setVolley_Type(strings.get(i));
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }


    private Map<String, List<OrderTitle>> map;

    private void setVolley_Type(final String s) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getOrderByType")
                .setJsonObject("type", s)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        List<OrderTitle> orderTitleList = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<OrderTitle>>() {
                                }.getType());
                        map.put(s, orderTitleList);
                        if (map.size() == strings.size()) {
                            setListAdapter();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setListAdapter() {
        final List<OrderTitle> orderTitleList = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            List<OrderTitle> orderTitles = map.get(strings.get(i));
            orderTitleList.addAll(orderTitles);
        }
        listView.setAdapter(new OrderTitleAdapter(this, orderTitleList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderDetailsActivity.newInstance(orderTitleList.get(position), OrderActivity.this);
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        listView = findViewById(R.id.list_view);
    }
}
