package com.example.smartcityb_2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.Jzk;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.Util;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 15:24
 */
public class ChratJzkActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText tvName;
    private EditText tvSex;
    private EditText tvId;
    private EditText tvBirth;
    private EditText tvTel;
    private EditText tvAddress;
    private TextView tvOnline;

    public static void newInstance(Jzk jzk, Context context, int lx) {
        Intent intent = new Intent(context, ChratJzkActivity.class);
        intent.putExtra("info", jzk);
        intent.putExtra("index", lx);
        context.startActivity(intent);

    }

    private int lx;
    private Jzk jzk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chreat_layout);
        initView();
        lx = getIntent().getIntExtra("index", 1);
        if (lx == 1) {
            jzk = (Jzk) getIntent().getSerializableExtra("info");
            title.setText("修改就诊卡");
            tvName.setText(jzk.getName());
            tvAddress.setText(jzk.getAddress());
            tvBirth.setText(jzk.getBirthday());
            tvId.setText(jzk.getID());
            tvSex.setText(jzk.getSex());
            tvTel.setText(jzk.getTel());
            tvOnline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VolleyTo volleyTo = new VolleyTo();
                    volleyTo.setUrl("updateCase")
                            //{"caseid1":"1111","caseid2":"2222",
                            // "name"="李昕","sex"="男","ID"="371402201006060606",
                            // "birthday"="2020-6-6","tel"="77777777777","address"="紫薇园"}
                            .setJsonObject("caseid1", jzk.getCaseId())
                            .setJsonObject("caseid2", "")
                            .setJsonObject("name", tvName.getText().toString())
                            .setJsonObject("sex", tvSex.getText().toString())
                            .setJsonObject("ID", tvId.getText().toString())
                            .setJsonObject("birthday", tvBirth.getText().toString())
                            .setJsonObject("tel", tvTel.getText().toString())
                            .setJsonObject("address", tvAddress.getText().toString())
                            .setVolleyLo(new VolleyLo() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    if (jsonObject.optString("RESULT").equals("S")) {
                                        Util.showToast("修改成功", ChratJzkActivity.this);
                                    } else {
                                        Util.showToast("修改失败", ChratJzkActivity.this);
                                    }
                                }

                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Util.showToast("修改成功", ChratJzkActivity.this);

                                }
                            }).start();

                }
            });

        } else {
            title.setText("创建就诊卡");
            tvOnline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(tvId.getText())) {
                        Util.showDialog("身份证号不能为空", ChratJzkActivity.this);
                        return;
                    }
                    VolleyTo volleyTo = new VolleyTo();
                    volleyTo.setUrl("createCase")
                            .setJsonObject("name", tvName.getText().toString())
                            .setJsonObject("sex", tvSex.getText().toString())
                            .setJsonObject("ID", tvId.getText().toString())
                            .setJsonObject("birthday", tvBirth.getText().toString())
                            .setJsonObject("tel", tvTel.getText().toString())
                            .setJsonObject("address", tvAddress.getText().toString())
                            .setVolleyLo(new VolleyLo() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    if (jsonObject.optString("RESULT").equals("S")) {
                                        Util.showToast("添加成功", ChratJzkActivity.this);
                                        finish();
                                    } else {
                                        Util.showToast("添加失败", ChratJzkActivity.this);
                                    }
                                }

                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Util.showToast("添加失败", ChratJzkActivity.this);
                                }
                            }).start();
                }
            });
        }
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvName = findViewById(R.id.tv_name);
        tvSex = findViewById(R.id.tv_sex);
        tvId = findViewById(R.id.tv_id);
        tvBirth = findViewById(R.id.tv_birth);
        tvTel = findViewById(R.id.tv_tel);
        tvAddress = findViewById(R.id.tv_address);
        tvOnline = findViewById(R.id.tv_online);
    }
}
