package com.example.smartcityb_2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.AppClient;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.adapter.JzkAdapter;
import com.example.smartcityb_2.bean.HospitalBean;
import com.example.smartcityb_2.bean.Jzk;
import com.example.smartcityb_2.bean.UserInfo;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.OnClickItem;
import com.example.smartcityb_2.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 15:14
 */
public class JzkActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;
    private Button btAdd;

    public static void newInstance(HospitalBean hospitalBean, Context context) {
        Intent intent = new Intent(context, JzkActivity.class);
        intent.putExtra("info", hospitalBean);
        context.startActivity(intent);
    }

    private HospitalBean hospitalBean;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jzk_layout);
        AppClient.add(this);
        initView();
        title.setText("就诊卡");
        hospitalBean = (HospitalBean) getIntent().getSerializableExtra("info");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChratJzkActivity.newInstance(jzks.get(0), JzkActivity.this, 2);
            }
        });
    }

    @Override
    protected void onResume() {
        setVolley();
        super.onResume();
    }

    UserInfo userInfo;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUserInfo")
                .setDialog(JzkActivity.this)
                .setJsonObject("userid", AppClient.getUserNum(AppClient.username))
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        userInfo = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).optJSONObject(0).toString()
                                , UserInfo.class);
                        setVolley_Jzk();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }


    List<Jzk> jzks;

    private void setVolley_Jzk() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("showCaseById")
                .setJsonObject("ID", userInfo.getId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        jzks = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<Jzk>>() {
                                }.getType());
                        JzkAdapter adapter = new JzkAdapter(JzkActivity.this, jzks);
                        listView.setAdapter(adapter);
                        adapter.setOnClickItem(new OnClickItem() {
                            @Override
                            public void onClick(int position, String name) {
                                //left
                                if (name.equals("1")) {
                                    DempartListActivity.newInstance(hospitalBean,userInfo,JzkActivity.this);
                                } else {
                                    ChratJzkActivity.newInstance(jzks.get(position), JzkActivity.this, 1);
                                }
                            }
                        });
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
        btAdd = findViewById(R.id.bt_add);
    }
}
