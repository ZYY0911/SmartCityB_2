package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.util.MyNetImage2;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 9:30
 */
public class PhotoAdapter extends ArrayAdapter<Integer> {

    public PhotoAdapter(@NonNull Context context, @NonNull List<Integer> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image, parent, false);
        MyNetImage2 itemImage = convertView.findViewById(R.id.item_image);
        itemImage.setImageResource(getItem(position));

        return convertView;
    }

    private void initView() {
    }
}
