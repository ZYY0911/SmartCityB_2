package com.example.smartcityb_2.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.AppClient;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.adapter.ChooseTimeAdapter;
import com.example.smartcityb_2.bean.ChooseTime;
import com.example.smartcityb_2.bean.DempartList;
import com.example.smartcityb_2.bean.HospitalBean;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 15:41
 */
public class YyghActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvZj;
    private TextView tvPt;
    private TextView tvZjDate;
    private LinearLayout layoutPt;
    private ListView listView;

    public static void newInstance(HospitalBean hospitalBean, UserInfo userInfo, DempartList dempartList, Context context) {
        Intent intent = new Intent(context, YyghActivity.class);
        intent.putExtra("info", hospitalBean);
        intent.putExtra("info2", dempartList);
        intent.putExtra("info3", userInfo);
        context.startActivity(intent);
    }

    private HospitalBean hospitalBean;
    private DempartList dempartList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dempartList = (DempartList) getIntent().getSerializableExtra("info2");
        hospitalBean = (HospitalBean) getIntent().getSerializableExtra("info");
        setContentView(R.layout.yygh_layout);
        initView();
        AppClient.add(this);
        title.setText("预约挂号");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvZj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvZj.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvPt.setTextColor(Color.parseColor("#333333"));
                tvZjDate.setVisibility(View.VISIBLE);
                layoutPt.setVisibility(View.GONE);
                tvZj.setBackgroundResource(R.drawable.text_line);
                tvPt.setBackgroundResource(R.drawable.text_no_line);
            }
        });
        tvPt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPt.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvZj.setTextColor(Color.parseColor("#333333"));
                layoutPt.setVisibility(View.VISIBLE);
                tvZjDate.setVisibility(View.GONE);
                tvPt.setBackgroundResource(R.drawable.text_line);
                tvZj.setBackgroundResource(R.drawable.text_no_line);
            }
        });
        setVolley();
    }


    List<ChooseTime> chooseTimes;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getDutyByDepartmentId")
                //{hospitalId:"1",departmentId:"1"}
                .setJsonObject("hospitalId", hospitalBean.getHospitalId())
                .setJsonObject("departmentId", dempartList.getDeptId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        chooseTimes = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<ChooseTime>>() {
                                }.getType());
                        ChooseTimeAdapter chooseTimeAdapter = new ChooseTimeAdapter(YyghActivity.this
                                , chooseTimes, dempartList.getDeptName());
                        listView.setAdapter(chooseTimeAdapter);
                        chooseTimeAdapter.setOnClickItem(new OnClickItem() {
                            @Override
                            public void onClick(final int position, String name) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(YyghActivity.this);
                                builder.setTitle("提示");
                                builder.setMessage("您确定要预约" + chooseTimes.get(position).getTime() + "," + dempartList.getDeptName() + "吗？");
                                builder.setPositiveButton("确实", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        YySeccessfulActivity.newInstance(hospitalBean, chooseTimes.get(position), dempartList, (UserInfo) getIntent().getSerializableExtra("info3"), YyghActivity.this);
                                    }
                                });

                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.show();
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
        tvZj = findViewById(R.id.tv_zj);
        tvPt = findViewById(R.id.tv_pt);
        tvZjDate = findViewById(R.id.tv_zj_date);
        layoutPt = findViewById(R.id.layout_pt);
        listView = findViewById(R.id.list_view);
    }
}
