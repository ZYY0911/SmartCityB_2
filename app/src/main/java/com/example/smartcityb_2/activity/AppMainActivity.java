package com.example.smartcityb_2.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.fragment.Allservcei;
import com.example.smartcityb_2.fragment.HdFragment;
import com.example.smartcityb_2.fragment.HomeFragment;
import com.example.smartcityb_2.fragment.MotifPwd;
import com.example.smartcityb_2.fragment.MyCenterFragment;
import com.example.smartcityb_2.fragment.MyInfoFragment;
import com.example.smartcityb_2.fragment.MzFragment;
import com.example.smartcityb_2.fragment.SmartYlFragment;
import com.example.smartcityb_2.fragment.WzcxFragemnt;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SNIHostName;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AppMainActivity extends AppCompatActivity {

    private LinearLayout layoutHead;
    private EditText etSearch;
    private FrameLayout frameLayout;
    private BottomNavigationView navBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        map = new HashMap<>();
        map.put("1", HomeFragment.newInstance(this));
        map.put("个人中心", MyCenterFragment.newInstance(this));
        map.put("个人信息", MyInfoFragment.newInstance(this));
        map.put("修改密码", MotifPwd.newInstance(this));
        map.put("活动", HdFragment.newInstance(this));
        map.put("违章查询", WzcxFragemnt.newInstance(this));
        map.put("门诊预约", MzFragment.newInstance(this));
        map.put("全部服务", Allservcei.newInstance("全部服务", this));
        map.put("新闻", Allservcei.newInstance("新闻", this));
        map.put("2", SmartYlFragment.newInstance(this));
        switchFragment(map.get("1"));
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        switchFragment(map.get("1"));
                        break;
                    case R.id.action_center:
                        switchFragment(map.get("个人中心"));
                        break;
                    case R.id.action_new:
                        switchFragment(map.get("新闻"));
                        break;
                    case R.id.action_service:
                        switchFragment(map.get("全部服务"));
                        break;
                    case R.id.action_fp:
                        switchFragment(map.get("2"));
                        break;

                }
                return true;
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public Map<String, Fragment> map;

    private Fragment currentFragment = new Fragment();

    public void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.frame_layout, fragment, fragment.getClass().getName());
        } else {
            transaction.hide(currentFragment).show(fragment);
        }
        if (!fragment.getClass().getName().equals(map.get("1").getClass().getName())) {
            layoutHead.setVisibility(View.GONE);
        } else {
            layoutHead.setVisibility(View.VISIBLE);
        }
        currentFragment = fragment;
        transaction.commit();
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    SearchNews.newInstance(etSearch.getText().toString()
                            , AppMainActivity.this);
                }
                return false;
            }
        });
    }


    public void onBack() {
        switchFragment(map.get("1"));

    }

    private void initView() {
        layoutHead = findViewById(R.id.layout_head);
        etSearch = findViewById(R.id.et_search);
        frameLayout = findViewById(R.id.frame_layout);
        navBottom = findViewById(R.id.nav_bottom);
    }
}