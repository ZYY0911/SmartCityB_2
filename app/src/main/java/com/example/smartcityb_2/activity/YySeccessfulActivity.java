package com.example.smartcityb_2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.AppClient;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.ChooseTime;
import com.example.smartcityb_2.bean.DempartList;
import com.example.smartcityb_2.bean.HospitalBean;
import com.example.smartcityb_2.bean.UserInfo;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.Util;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 15:52
 */
public class YySeccessfulActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvMsg;
    private TextView tvOnline;

    public static void newInstance(HospitalBean hospitalBean, ChooseTime chooseTime, DempartList dempartList, UserInfo userInfo, Context context) {
        Intent intent = new Intent(context, YySeccessfulActivity.class);
        intent.putExtra("info", hospitalBean);
        intent.putExtra("info2", chooseTime);
        intent.putExtra("info3", userInfo);
        intent.putExtra("info4", dempartList);
        context.startActivity(intent);
    }

    HospitalBean hospitalBean;
    ChooseTime chooseTime;
    UserInfo userInfo;
    DempartList dempartList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yysuccess_layout);
        AppClient.add(this);
        initView();
        title.setText("预约挂号");
        tvOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppClient.finAll();
            }
        });
        chooseTime = (ChooseTime) getIntent().getSerializableExtra("info2");
        hospitalBean = (HospitalBean) getIntent().getSerializableExtra("info");
        userInfo = (UserInfo) getIntent().getSerializableExtra("info3");
        dempartList = (DempartList) getIntent().getSerializableExtra("info4");
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("appointment")
                //{"pid":"371402199902041133","name":"赵子涵","phone":"13505347777","doctorId":2,"appTime":"2020-10-2 周四 下午14：00"}
                .setJsonObject("pid", userInfo.getId())
                .setJsonObject("name", userInfo.getName())
                .setJsonObject("phone", userInfo.getPhone())
                .setJsonObject("doctorId", chooseTime.getDoctorId())
                .setJsonObject("appTime", chooseTime.getTime())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            tvMsg.setText("预约结果" + "\r\n\r\n" +
                                    "预约科室：" + dempartList.getDeptName() + "\r\n"
                                    + "门诊类型：" + chooseTime.getType() + "\r\n"
                                    + "预约时间：" + jsonObject.optJSONObject("data")
                                    .optString("appTime"));
                        } else {
                            Util.showToast("预约失败", YySeccessfulActivity.this);
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Util.showToast("预约失败", YySeccessfulActivity.this);

                    }
                }).start();
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvMsg = findViewById(R.id.tv_msg);
        tvOnline = findViewById(R.id.tv_online);
    }

    //{"pid":"371402199902041133","name":"赵子涵","phone":"13505347777","doctorId":2,"appTime":"2020-10-2 周四 下午14：00"}

}
