package com.example.smartcityb_2.fragment_guild;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.activity.AppMainActivity;
import com.example.smartcityb_2.dialog.NetDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 7:36
 */
public class GuildFragment2 extends Fragment {
    private ImageView itemImage;
    private ViewPager viewPager;
    private Button btSetting;
    private Button btMain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.guild_layout2, container, false);
    }


    private int image;

    public GuildFragment2(int image) {
        this.image = image;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        itemImage.setImageResource(image);
        btMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AppMainActivity.class));
                getActivity().finish();
            }
        });
        btSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetDialog dialog = new NetDialog();
                dialog.show(getChildFragmentManager(), "aaa");
            }
        });
    }

    private void initView() {
        itemImage = getView().findViewById(R.id.item_image);
        viewPager = getView().findViewById(R.id.view_pager);
        btSetting = getView().findViewById(R.id.bt_setting);
        btMain = getView().findViewById(R.id.bt_main);
    }
}
