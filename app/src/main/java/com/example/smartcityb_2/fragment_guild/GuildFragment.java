package com.example.smartcityb_2.fragment_guild;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.smartcityb_2.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 7:36
 */
public class GuildFragment extends Fragment {
    private ImageView itemImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.guild_fragment, container, false);
    }


    private int image;

    public GuildFragment(int image) {
        this.image = image;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        itemImage.setImageResource(image);
    }

    private void initView() {
        itemImage = getView().findViewById(R.id.item_image);
    }
}
