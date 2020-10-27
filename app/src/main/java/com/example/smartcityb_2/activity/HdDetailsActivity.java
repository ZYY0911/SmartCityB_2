package com.example.smartcityb_2.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.adapter.HdAdapter;
import com.example.smartcityb_2.adapter.PLAdapter;
import com.example.smartcityb_2.bean.HdDetails;
import com.example.smartcityb_2.bean.Hdpl;
import com.example.smartcityb_2.dialog.PlDialog;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.MyListView;
import com.example.smartcityb_2.util.MyNetImage2;
import com.example.smartcityb_2.util.OnClickItem;
import com.example.smartcityb_2.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 10:46
 */
public class HdDetailsActivity extends AppCompatActivity {

    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private MyNetImage2 ivPhoto;
    private TextView tvMsg;
    private TextView tvPl;
    private MyListView plList;
    private MyListView tjList;
    private TextView etPl;
    private ScrollView scrollView;

    public static void newInstance(HdDetails hdDetails, Context context) {
        Intent intent = new Intent(context, HdDetailsActivity.class);
        intent.putExtra("info", hdDetails);
        context.startActivity(intent);
    }


    private HdDetails hdDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hd_detalis_layout);
        hdDetails = (HdDetails) getIntent().getSerializableExtra("info");

        initView();
        title1.setText("报名活动");
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HdDetailsActivity.this);
                builder.setTitle("你确定要报名：");
                builder.setMessage(hdDetails.getName() + "活动吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        VolleyTo volleyTo = new VolleyTo();
                        volleyTo.setUrl("setActionSignUpCount")
                                //{"id":"2","userid":"4"}
                                .setJsonObject("id", hdDetails.getId())
                                .setVolleyLo(new VolleyLo() {
                                    @Override
                                    public void onResponse(JSONObject jsonObject) {
                                        if (jsonObject.optString("RESULT").equals("S")) {
                                            Util.showToast("报名成功", HdDetailsActivity.this);
                                        } else {
                                            Util.showToast("报名失败", HdDetailsActivity.this);
                                        }
                                    }

                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {
                                        Util.showToast("报名失败", HdDetailsActivity.this);

                                    }
                                }).start();
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
        title.setText(hdDetails.getName());
        tvMsg.setText(hdDetails.getDescription());
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivPhoto.setImageUrl(hdDetails.getImage());
        setVolley();
        etPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlDialog dialog = new PlDialog(hdDetails.getId());
                dialog.show(getSupportFragmentManager(), "aaa");
                dialog.setOnClickItem(new OnClickItem() {
                    @Override
                    public void onClick(int position, String name) {
                        if (position == 1) {
                            setVolley();
                        }
                    }
                });
            }
        });
        setTj();

    }


    List<HdDetails> tjLsit;
    Random random = new Random();

    private void setTj() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getRecommandAction")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tjLsit = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<HdDetails>>() {
                                }.getType());
                        for (int i = 0; i < tjLsit.size(); i++) {
                            if (tjLsit.get(i).getId() == hdDetails.getId()) {
                                tjLsit.remove(i);
                            }
                        }
                        final List<HdDetails> detailsList = new ArrayList<>();
                        for (int i = 0; i < random.nextInt(3) + 1; i++) {
                            detailsList.add(tjLsit.get(i));
                        }
                        tjList.setAdapter(new HdAdapter(HdDetailsActivity.this, detailsList));
                        tjList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                HdDetailsActivity.newInstance(detailsList.get(position), HdDetailsActivity.this);
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }


    List<Hdpl> hdpls;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getActionCommitById")
                .setJsonObject("id", hdDetails.getId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hdpls = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<Hdpl>>() {
                                }.getType());
                        tvPl.setText("评论(" + hdpls.size() + ")");
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
                        plList.setAdapter(new PLAdapter(HdDetailsActivity.this, hdpls));
                      scrollView  .post(new Runnable() {
                            @Override
                            public void run() {
                                scrollView.fullScroll(View.FOCUS_UP);
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
        ivPhoto = findViewById(R.id.iv_photo);
        tvMsg = findViewById(R.id.tv_msg);
        tvPl = findViewById(R.id.tv_pl);
        plList = findViewById(R.id.pl_list);
        tjList = findViewById(R.id.tj_list);
        etPl = findViewById(R.id.et_pl);
        scrollView = findViewById(R.id.scroll_view);
    }
}
