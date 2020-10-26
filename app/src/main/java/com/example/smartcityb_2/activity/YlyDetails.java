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
    }
}
