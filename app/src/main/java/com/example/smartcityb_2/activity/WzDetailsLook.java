package com.example.smartcityb_2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.Clwz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 14:31
 */
public class WzDetailsLook extends AppCompatActivity {
    private TextView tvCp;
    private TextView tvWzsh;
    private TextView tvSj;
    private TextView tvDd;
    private TextView tvXw;
    private TextView tvFk;
    private TextView tvJf;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;

    public static void newInstance(Clwz clwz, Context context) {
        Intent intent = new Intent(context, WzDetailsLook.class);
        intent.putExtra("info", clwz);
        context.startActivity(intent);
    }

    Clwz clwz;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_details_look);

        initView();
        clwz = (Clwz) getIntent().getSerializableExtra("info");
        title.setText("违法详情");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvCp.setText("车牌号："+clwz.getCarid());
        tvWzsh.setText("通知书号："+clwz.getNotification());
        tvSj.setText("违章时间："+clwz.getTime());
        tvDd.setText("违章地点："+clwz.getPlace());
        tvXw.setText("违章行为："+clwz.getDescription());
        tvFk.setText("罚款金额："+clwz.getCost());
        tvJf.setText("违章记分："+clwz.getDeductPoints());
    }

    private void initView() {
        tvCp = findViewById(R.id.tv_cp);
        tvWzsh = findViewById(R.id.tv_wzsh);
        tvSj = findViewById(R.id.tv_sj);
        tvDd = findViewById(R.id.tv_dd);
        tvXw = findViewById(R.id.tv_xw);
        tvFk = findViewById(R.id.tv_fk);
        tvJf = findViewById(R.id.tv_jf);
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
    }
}
