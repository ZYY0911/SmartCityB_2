package com.example.smartcityb_2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.adapter.PhotoAdapter;
import com.example.smartcityb_2.bean.UserInfo;
import com.example.smartcityb_2.util.MyNetImage2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 9:25
 */
public class ChoosePhoto extends AppCompatActivity {

    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private GridView girdPhoto;
    private LinearLayout layoutInfo;
    private MyNetImage2 ivPhoto;

    public static void newInstance(UserInfo msg, Context context,int code) {
        Intent intent = new Intent(context, ChoosePhoto.class);
        intent.putExtra("info", msg);
        context.startActivity(intent);
    }

    private Integer images[] = {R.mipmap.user1, R.mipmap.user2,
            R.mipmap.user3, R.mipmap.user4, R.mipmap.user5, R.mipmap.user6, R.mipmap.user7, R.mipmap.user8};
    private List<Integer> integers;

    private UserInfo userInfo;
    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoose_photo);
        userInfo = (UserInfo) getIntent().getSerializableExtra("info");
        initView();
        integers = new ArrayList<>();
        integers.addAll(Arrays.asList(images));
        title.setText("更换头像");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("date", ivPhoto.getTag().toString());
                intent.putExtra("index", index);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        ivPhoto.setImageUrl(userInfo.getAvatar());
        girdPhoto.setAdapter(new PhotoAdapter(this, integers));
        girdPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ivPhoto.setTag("user" + (position + 1) + ".png");
                index = position;
                ivPhoto.setImageResource(integers.get(position));
            }
        });
        ivPhoto.setTag(userInfo.getAvatar().substring(userInfo.getAvatar().lastIndexOf("/")));
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        girdPhoto = findViewById(R.id.gird_photo);
        layoutInfo = findViewById(R.id.layout_info);
        ivPhoto = findViewById(R.id.iv_photo);
    }
}
