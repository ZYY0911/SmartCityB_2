package com.example.smartcityb_2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.ServiceInfo;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.Util;
import com.google.gson.Gson;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 16:15
 */
public class WebEmptyActivity extends AppCompatActivity {
    private WebView webView;

    public static void newInstance(String url, Context context) {
        Intent intent = new Intent(context, WebEmptyActivity.class);
        intent.putExtra("info", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);
        initView();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("service_info")
                .setJsonObject("serviceid", getIntent().getStringExtra("info"))
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ServiceInfo serviceInfo = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).optJSONObject(0).toString()
                                , ServiceInfo.class);
                        webView.loadUrl(serviceInfo.getUrl());
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initView() {
        webView = findViewById(R.id.web_view);
    }
}
