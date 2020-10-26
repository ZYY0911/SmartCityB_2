package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.PtjcBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 17:04
 */
public class PtjcAdapter extends ArrayAdapter<PtjcBean> {

    public PtjcAdapter(@NonNull Context context, @NonNull List<PtjcBean> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.ptjc_item, parent, false);
        RelativeLayout btColor = convertView.findViewById(R.id.bt_color);
        TextView itemName = convertView.findViewById(R.id.item_name);
        TextView itemNum = convertView.findViewById(R.id.item_num);
        PtjcBean ptjcBean = getItem(position);
        btColor.setBackgroundColor(ptjcBean.getCoolor());
        itemNum.setText(ptjcBean.getValue() + "");
        itemName.setText(ptjcBean.getName());
        return convertView;
    }

    private void initView() {


    }
}
