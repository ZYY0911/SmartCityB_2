package com.example.smartcityb_2.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.AppClient;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.Util;

import org.json.JSONObject;

import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 10:10
 */
public class YjfkActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText etMsg;
    private TextView tvNum;
    private Button btSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yjdk_layout);
        initView();
        title.setText("意见反馈");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etMsg.getText())) {
                    Util.showToast("意见不能为空", YjfkActivity.this);
                    return;
                }
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("publicOpinion")
                        .setDialog(YjfkActivity.this)
                        //{"userid":"1","content":"内容","time":"yyyy.MM.dd HH:mm:ss"}
                        .setJsonObject("userid", AppClient.getUserNum(AppClient.username))
                        .setJsonObject("content", etMsg.getText().toString())
                        .setJsonObject("time", Util.simpleDate("yyyy.MM.dd HH:mm:ss", new Date()))
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Util.showToast("提交成功", YjfkActivity.this);
                                    etMsg.setText("");
                                } else {
                                    Util.showToast("提交失败", YjfkActivity.this);
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Util.showToast("提交失败", YjfkActivity.this);

                            }
                        }).start();
            }
        });
        etMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    String msg = s.toString();
                    if (msg.length() >= 151) {
                        Util.showToast("只能输入150字", YjfkActivity.this);
                        etMsg.setText(msg.substring(0, 150));
                        etMsg.setSelection(150);
                        return;
                    }
                    tvNum.setText(msg.length() + "/150字");
                }
            }
        });

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        etMsg = findViewById(R.id.et_msg);
        tvNum = findViewById(R.id.tv_num);
        btSave = findViewById(R.id.bt_save);
    }
}
