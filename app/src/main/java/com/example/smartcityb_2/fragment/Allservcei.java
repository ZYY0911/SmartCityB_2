package com.example.smartcityb_2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.activity.AppMainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 16:18
 */
public class Allservcei extends Fragment {
    private TextView title;
    private TextView title1;
    private String name;
    private AppMainActivity appMainActivity;

    public Allservcei() {
    }

    public Allservcei(String name, AppMainActivity appMainActivity) {
        this.name = name;
        this.appMainActivity = appMainActivity;
    }

    public static Allservcei newInstance(String name, AppMainActivity appMainActivity) {
        return new Allservcei(name, appMainActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mpty_fragment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText(name);

    }

    private void initView() {
        title = getView().findViewById(R.id.title);
        title1 =getView(). findViewById(R.id.title1);
    }
}
