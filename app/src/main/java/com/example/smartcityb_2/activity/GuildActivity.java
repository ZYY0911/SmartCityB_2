package com.example.smartcityb_2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.smartcityb_2.AppClient;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.fragment_guild.GuildFragment;
import com.example.smartcityb_2.fragment_guild.GuildFragment2;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 7:35
 */
public class GuildActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private int iamge[] = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e};

    private SharedPreferences preferences;
    private List<Fragment> fragments;
    private LinearLayout layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guild_layout);
        preferences = AppClient.sharedPreferences;
        if (preferences.getBoolean(AppClient.IsFirst, true)) {
            preferences.edit().putBoolean(AppClient.IsFirst, false).apply();
        } else {
            startActivity(new Intent(this, AppMainActivity.class));
            finish();
            return;
        }
        initView();
        fragments = new ArrayList<>();
        for (int i = 0; i < iamge.length; i++) {
            if (i != iamge.length - 1) {
                fragments.add(new GuildFragment(iamge[i]));
            } else {
                fragments.add(new GuildFragment2(iamge[i]));
            }
            ImageView imageView = new ImageView(this);
            if (i == 0) {
                imageView.setImageResource(R.drawable.selsect_image);
            } else {
                imageView.setImageResource(R.drawable.no_selsect_image);
            }
            layout.addView(imageView);
        }
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < layout.getChildCount(); i++) {
                    ImageView imageView = (ImageView) layout.getChildAt(i);
                    if (i == position) {
                        imageView.setImageResource(R.drawable.selsect_image);
                    } else {
                        imageView.setImageResource(R.drawable.no_selsect_image);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        viewPager = findViewById(R.id.view_pager);
        layout = findViewById(R.id.layout);
    }
}
