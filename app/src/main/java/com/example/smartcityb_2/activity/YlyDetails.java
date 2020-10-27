package com.example.smartcityb_2.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.YylItem;
import com.example.smartcityb_2.dialog.YyDialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 16:36
 */
public
class YlyDetails extends AppCompatActivity {
    private ImageView ivPhoto;
    private TextView tvMsg;
    private Button btYy;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvPtss;
    private TextView tvFwtx;
    private TextView tvTitle;

    public static void newInstance(YylItem image, Context context) {
        Intent intent = new Intent(context, YlyDetails.class);
        intent.putExtra("info", image);
        context.startActivity(intent);
    }

    private YylItem yylItem;

    private String msg = "独立卫浴\n" +
            "活动室\n" +
            "健身房\n" +
            "舞蹈房\n" +
            "麻将室";
    private String msg2 = "全天24小时\n" +
            "20多名专家100多名护士\n" +
            "心理咨询\n" +
            "活动社团";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yll_detials);
        initView();
        yylItem = (YylItem) getIntent().getSerializableExtra("info");
        title.setText(yylItem.getName());
        ivPhoto.setImageResource(yylItem.getImage());
        tvMsg.setText(msg);
        tvPtss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMsg.setText(msg);
                tvPtss.setBackgroundResource(R.drawable.text_line);
                tvPtss.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvFwtx.setTextColor(Color.parseColor("#333333"));
                tvFwtx.setBackgroundResource(R.drawable.text_no_line);
            }
        });
        tvTitle.setText("“智慧养老”是面向居家老人、社区及养老机构提供实时、快捷、高效、低成本智能化的养老服务平台。\n" +
                "随着经济的发展，人民的生活水平得到普遍提高，随之而来的是我国的老龄化程度越来越高，人们已经普遍意识到老龄化问题将会带来的问题。整个社会在趋向于“衰老型”发展，人口老龄化的问题日益严重，空巢老人的现象也日益加剧。呈现出老年人口基数大、增速快、高龄化、失能化、空巢化趋势明显的态势，再加上我国未富先老的国情和家庭小型化的结构叠加在一起，养老问题异常严峻。智慧养老平台主要围绕着利用先进的信息技术手段实现“以入住老人为中心，规范养老服务，强化养老管理”，同时，针对老年人心理生理特点，以信息化技术为核心，采用先进的计算机技术、通信技术、无线传输技术、控制技术，为老人提供一个安全、便捷、高效、舒适的养老综合服务。\n");
        tvFwtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMsg.setText(msg2);
                tvPtss.setTextColor(Color.parseColor("#333333"));
                tvFwtx.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvFwtx.setBackgroundResource(R.drawable.text_line);
                tvPtss.setBackgroundResource(R.drawable.text_no_line);
            }
        });
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btYy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YyDialog dialog = new YyDialog();
                dialog.show(getSupportFragmentManager(), "");
            }
        });

    }

    private void initView() {
        ivPhoto = findViewById(R.id.iv_photo);
        tvMsg = findViewById(R.id.tv_msg);
        btYy = findViewById(R.id.bt_yy);
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvPtss = findViewById(R.id.tv_ptss);
        tvFwtx = findViewById(R.id.tv_fwtx);
        tvTitle = findViewById(R.id.tv_title);
    }
}
