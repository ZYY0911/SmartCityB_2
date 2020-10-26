package com.example.smartcityb_2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.adapter.OrderDetialsAdapter;
import com.example.smartcityb_2.bean.OrderDetials;
import com.example.smartcityb_2.bean.OrderTitle;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 9:41
 */
public class OrderDetailsActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;
    private TextView tvDdh;
    private TextView tvDdlx;
    private TextView tvRq;

    public static void newInstance(OrderTitle orderTitle, Context context) {
        Intent intent = new Intent(context, OrderDetailsActivity.class);
        intent.putExtra("info", orderTitle);
        context.startActivity(intent);
    }

    OrderTitle orderTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_drtails_activityu);
        initView();
        title.setText("订单详情");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        orderTitle = (OrderTitle) getIntent().getSerializableExtra("info");
        tvDdh.setText("订单号：" + orderTitle.getId());
        tvDdlx.setText("订单类型：" + orderTitle.getType());
        tvRq.setText("日期：" + orderTitle.getDate());
        setVolley();
    }


    List<OrderDetials> orderDetials;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getOrderById")
                .setJsonObject("id", orderTitle.getId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        orderDetials = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<OrderDetials>>() {
                                }.getType());
                        listView.setAdapter(new OrderDetialsAdapter(OrderDetailsActivity.this
                                , orderDetials));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        listView = findViewById(R.id.list_view);
        tvDdh = findViewById(R.id.tv_ddh);
        tvDdlx = findViewById(R.id.tv_ddlx);
        tvRq = findViewById(R.id.tv_rq);
    }
}
