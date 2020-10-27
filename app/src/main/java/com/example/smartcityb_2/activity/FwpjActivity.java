package com.example.smartcityb_2.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.adapter.PLAdapter;
import com.example.smartcityb_2.bean.Hdpl;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/27 at 7:59
 */
public class FwpjActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private Button btSubmit;
    private ListView listView;
    private EditText etMsg;
    private TextView tvPl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fwpj_layouty);

        initView();
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.showToast("提交成功", FwpjActivity.this);
                hdpls.add(0,new Hdpl(1,1,"abc",Util.simpleDate("yyyy-MM-dd HH:mm:ss",new Date())
                        ,etMsg.getText().toString()));
                adapter.notifyDataSetChanged();
                etMsg.setText("");

            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        title.setText("服务评价");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setVolley();
    }

    List<Hdpl> hdpls;
    private PLAdapter adapter;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getActionCommitById")
                .setJsonObject("id", 1)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hdpls = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<Hdpl>>() {
                                }.getType());
                        Collections.sort(hdpls, new Comparator<Hdpl>() {
                            @Override
                            public int compare(Hdpl o1, Hdpl o2) {
                                Date date1 = null, date2 = null;
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                try {
                                    date1 = format.parse(o1.getCommitTime());
                                    date2 = format.parse(o2.getCommitTime());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                return date2.compareTo(date1);
                            }
                        });
                        adapter =new PLAdapter(FwpjActivity.this, hdpls);
                        listView.setAdapter(adapter);

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
        btSubmit = findViewById(R.id.bt_submit);
        listView = findViewById(R.id.list_view);
        etMsg = findViewById(R.id.et_msg);
        tvPl = findViewById(R.id.tv_pl);
    }
}
