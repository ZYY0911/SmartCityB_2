package com.example.smartcityb_2.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.adapter.FwjlAdapter;
import com.example.smartcityb_2.adapter.RlAdapter;
import com.example.smartcityb_2.bean.Fwjl;
import com.example.smartcityb_2.bean.RlBean;
import com.example.smartcityb_2.util.MyGirdView;
import com.example.smartcityb_2.util.OnClickItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/27 at 7:27
 */
public class FwjlActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private MyGirdView rlGidr;
    private ListView listView;

    private List<RlBean> rlBeans;
    private List<Integer> integers;
    RlAdapter adapter;
    private TextView tvXjll;
    private TextView tvCyjl;

    private List<Fwjl> fwjls1, fwjls2;
    private List<Fwjl> cyjls1, cyjls2;
    private int lx = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fwjl_layout);
        initView();
        title.setText("服务记录");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fwjls1 = new ArrayList<>();
        fwjls2 = new ArrayList<>();
        cyjls1 = new ArrayList<>();
        cyjls2 = new ArrayList<>();
        fwjls1.add(new Fwjl("早餐:包子", "服务人员：张三"));
        fwjls1.add(new Fwjl("午餐:米饭，茄子", "服务人员：张三"));
        fwjls1.add(new Fwjl("晚餐:面条", "服务人员：王五"));
        fwjls2.add(new Fwjl("早餐:面条", "服务人员：李四"));
        fwjls2.add(new Fwjl("午餐:米饭，茄子", "服务人员：李四"));
        fwjls2.add(new Fwjl("晚餐:饺子", "服务人员：张三"));
        cyjls1.add(new Fwjl("早:房间卫生打扫", "服务人员：李四"));
        cyjls1.add(new Fwjl("中：通风", "服务人员：李四"));
        cyjls1.add(new Fwjl("晚：关灯", "服务人员：张三"));
        cyjls2.add(new Fwjl("早:房间卫生打扫", "服务人员：张三"));
        cyjls2.add(new Fwjl("中：更换床单", "服务人员：张三"));
        cyjls2.add(new Fwjl("晚:拖地，关灯", "服务人员：王五"));
        rlBeans = new ArrayList<>();
        integers = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-20);
        for (int i = 0; i < calendar.get(Calendar.DAY_OF_WEEK) - 1; i++) {
            rlBeans.add(new RlBean(i, 0, 0, 3));
            integers.add(3);
        }
        for (int i = 0; i < 21; i++) {
            rlBeans.add(new RlBean(i, calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.MONTH) + 1, i==20?2:getBg(calendar)));

            integers.add(getBg(calendar));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        for (int i = 0; i < 7; i++) {
            rlBeans.add(new RlBean(i, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, 3));
            integers.add(3);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        int count = 35 - rlBeans.size();
        if (rlBeans.size() != 42) {
            for (int i = 0; i < count; i++) {
                rlBeans.add(new RlBean(i, 0, 0, 3));
                integers.add(3);
            }
        }
        adapter = new RlAdapter(FwjlActivity.this, rlBeans);
        rlGidr.setAdapter(adapter);
        adapter.setOnClickItem(new OnClickItem() {
            @Override
            public void onClick(int position, String name) {
                RlBean rlBean = rlBeans.get(position);
                if (rlBean.getBg() == 2) {
                    rlBean.setBg(integers.get(position));
                } else {
                    for (int i = 0; i < rlBeans.size(); i++) {
                        RlBean rlBean1 = rlBeans
                                .get(i);
                        rlBean1.setBg(integers.get(i));
                        rlBeans.set(i, rlBean1);
                    }
                    rlBean.setBg(2);
                }

                tvXjll.setBackgroundResource(R.drawable.text_line);
                tvXjll.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvCyjl.setTextColor(Color.parseColor("#333333"));
                tvCyjl.setBackgroundResource(R.drawable.text_no_line);
                if (rlBean.getDay() % 2 == 0) {
                    listView.setAdapter(new FwjlAdapter(FwjlActivity.this, fwjls1));
                    lx = 0;
                } else {
                    listView.setAdapter(new FwjlAdapter(FwjlActivity.this, fwjls2));
                    lx = 1;
                }
                rlBeans.set(position, rlBean);
                adapter.notifyDataSetChanged();
            }
        });
        tvXjll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvXjll.setBackgroundResource(R.drawable.text_line);
                tvXjll.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvCyjl.setTextColor(Color.parseColor("#333333"));
                tvCyjl.setBackgroundResource(R.drawable.text_no_line);
                if (lx == 0) {
                    listView.setAdapter(new FwjlAdapter(FwjlActivity.this, fwjls1));
                } else {
                    listView.setAdapter(new FwjlAdapter(FwjlActivity.this, fwjls2));
                }
            }
        });
        tvCyjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvXjll.setTextColor(Color.parseColor("#333333"));
                tvCyjl.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvCyjl.setBackgroundResource(R.drawable.text_line);
                tvXjll.setBackgroundResource(R.drawable.text_no_line);
                if (lx == 0) {
                    listView.setAdapter(new FwjlAdapter(FwjlActivity.this, cyjls1));
                } else {
                    listView.setAdapter(new FwjlAdapter(FwjlActivity.this, cyjls2));
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(FwjlActivity.this, FwpjActivity.class));
            }
        });
        listView.setAdapter(new FwjlAdapter(FwjlActivity.this, fwjls1));
    }


    private int getBg(Calendar calendar) {
        if (calendar.get(Calendar.DAY_OF_WEEK) == 7
                || calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            return 1;
        } else  {
            return 0;
        }
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        rlGidr = findViewById(R.id.rl_gidr);
        listView = findViewById(R.id.list_view);
        tvCyjl = findViewById(R.id.tv_cyjl);
        tvXjll = findViewById(R.id.tv_xjll);
        tvCyjl = findViewById(R.id.tv_cyjl);
    }
}
