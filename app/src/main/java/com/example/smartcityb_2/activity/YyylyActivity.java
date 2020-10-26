package com.example.smartcityb_2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.adapter.YylAdapter;
import com.example.smartcityb_2.bean.YylItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 16:30
 */
public
class YyylyActivity extends AppCompatActivity {
    private TextView title;
    private TextView title1;
    private ListView listView;
    private ImageView itemChange;
    List<YylItem> yylItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yyyly_layout);
        initView();
        title.setText("预约养老院");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        yylItems = new ArrayList<>();
        yylItems.add(new YylItem(R.mipmap.a, "养老院1", "山东省"));
        yylItems.add(new YylItem(R.mipmap.b, "养老院2", "北京市"));
        listView.setAdapter(new YylAdapter(this, yylItems));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                YlyDetails.newInstance(yylItems.get(position), YyylyActivity.this);
            }
        });
    }

    private void initView() {
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        listView = findViewById(R.id.list_view);
        itemChange = findViewById(R.id.item_change);
    }
}
