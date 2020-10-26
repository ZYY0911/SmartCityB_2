package com.example.smartcityb_2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.AppClient;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.adapter.DempartAdapter;
import com.example.smartcityb_2.bean.DempartList;
import com.example.smartcityb_2.bean.HospitalBean;
import com.example.smartcityb_2.bean.Jzk;
import com.example.smartcityb_2.bean.UserInfo;
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
 * @Create by 张瀛煜 on 2020/10/26 at 15:35
 */
public class DempartListActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;

    public static void newInstance(HospitalBean hospitalBean, UserInfo userInfo, Context context) {
        Intent intent = new Intent(context, DempartListActivity.class);
        intent.putExtra("info", hospitalBean);
        intent.putExtra("info3", userInfo);
        context.startActivity(intent);
    }

    private HospitalBean hospitalBean;

    List<DempartList> dempartLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dempart_layout);
        AppClient.add(this);
        hospitalBean = (HospitalBean) getIntent().getSerializableExtra("info");
        initView();
        title.setText("科室列表");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getHospitalDepartmentByHospitalId")
                .setJsonObject("hospitalId", hospitalBean.getHospitalId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        dempartLists = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<DempartList>>() {
                                }.getType());
                        listView.setAdapter(new DempartAdapter(DempartListActivity.this, dempartLists));
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                YyghActivity.newInstance(hospitalBean, (UserInfo) getIntent().getSerializableExtra("info3"), dempartLists.get(position), DempartListActivity.this);
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
    }
}
