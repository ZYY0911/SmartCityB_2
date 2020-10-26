package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.YylItem;
import com.example.smartcityb_2.util.MyNetImage2;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 16:34
 */
public class YylAdapter extends ArrayAdapter<YylItem> {

    public YylAdapter(@NonNull Context context, @NonNull List<YylItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.yyl_item, parent, false);
        MyNetImage2 itemImage = convertView.findViewById(R.id.item_image);
        TextView itemName = convertView.findViewById(R.id.item_name);
        TextView itemAddress = convertView.findViewById(R.id.item_address);
        YylItem yylItem = getItem(position);
        itemImage.setImageResource(yylItem.getImage());
        itemName.setText(yylItem.getName());
        itemAddress.setText(yylItem.getAddress());
        return convertView;
    }


}
