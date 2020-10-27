package com.example.smartcityb_2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.activity.AppMainActivity;
import com.example.smartcityb_2.activity.FwjlActivity;
import com.example.smartcityb_2.activity.JzjcActivity;
import com.example.smartcityb_2.activity.PPtjcActivity;
import com.example.smartcityb_2.activity.YyylyActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 16:21
 */
public class SmartYlFragment extends Fragment {
    private TextView title;
    private TextView title1;
    private LinearLayout layoutYyyl;
    private LinearLayout layoutPtjc;
    private LinearLayout layoutFwjl;
    private LinearLayout layoutJzjc;

    public SmartYlFragment() {
    }

    private AppMainActivity appMainActivity;

    public SmartYlFragment(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }

    public static SmartYlFragment newInstance(AppMainActivity appMainActivity) {
        return new SmartYlFragment(appMainActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.smart_yl_fragment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("智慧养老");
        layoutYyyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), YyylyActivity.class));
            }
        });
        layoutPtjc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PPtjcActivity.class));
            }
        });
        layoutJzjc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), JzjcActivity.class));
            }
        });
        layoutFwjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FwjlActivity.class));
            }
        });
    }

    private void initView() {
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        layoutYyyl = getView().findViewById(R.id.layout_yyyl);
        layoutPtjc = getView().findViewById(R.id.layout_ptjc);
        layoutFwjl = getView().findViewById(R.id.layout_fwjl);
        layoutJzjc = getView().findViewById(R.id.layout_jzjc);
    }
}
