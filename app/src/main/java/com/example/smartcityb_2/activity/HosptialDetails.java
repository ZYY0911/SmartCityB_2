package com.example.smartcityb_2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.AppClient;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.HospitalBean;
import com.example.smartcityb_2.bean.HosptialImage;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.MyNetImage;
import com.example.smartcityb_2.util.Util;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 15:03
 */
public class HosptialDetails extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ViewFlipper viewFlipper;
    private TextView tvMsg;
    private Button btYy;

    public static void newInstance(HospitalBean hospitalBean, Context context) {
        Intent intent = new Intent(context, HosptialDetails.class);
        intent.putExtra("info", hospitalBean);
        context.startActivity(intent);
    }


    private HospitalBean hospitalBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosptial_detalis);
        AppClient.add(this);
        hospitalBean = (HospitalBean) getIntent().getSerializableExtra("info");
        initView();
        title.setText("医院简介");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setVoley_Image();
        tvMsg.setText(hospitalBean.getDesc());
        btYy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JzkActivity.newInstance(hospitalBean, HosptialDetails.this);
            }
        });
    }

    HosptialImage hosptialImage;
    List<String> strings;

    private void setVoley_Image() {
        strings = new ArrayList<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getImagesByHospitalId")
                .setJsonObject("hospitalId", hospitalBean.getHospitalId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hosptialImage = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).optJSONObject(0).toString()
                                , HosptialImage.class);
                        strings.add(hosptialImage.getImage1());
                        strings.add(hosptialImage.getImage2());
                        strings.add(hosptialImage.getImage3());
                        strings.add(hosptialImage.getImage4());
                        for (int i = 0; i < strings.size(); i++) {
                            MyNetImage image = new MyNetImage(HosptialDetails.this);
                            image.setImageUrl(strings.get(i));
                            viewFlipper.addView(image);
                        }
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
        viewFlipper = findViewById(R.id.view_flipper);
        tvMsg = findViewById(R.id.tv_msg);
        btYy = findViewById(R.id.bt_yy);
    }
}
