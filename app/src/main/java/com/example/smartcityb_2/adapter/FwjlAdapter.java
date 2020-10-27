package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.Fwjl;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/27 at 7:56
 */
public class FwjlAdapter extends ArrayAdapter<Fwjl> {

    public FwjlAdapter(@NonNull Context context, @NonNull List<Fwjl> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.fwjl_item, parent, false);
        TextView itemTitle = convertView.findViewById(R.id.item_title);
        TextView itemContext = convertView.findViewById(R.id.item_context);
        Fwjl fwjl = getItem(position);
        itemTitle.setText(fwjl.getName());
        itemContext.setText(fwjl.getMsg());
        return convertView;
    }

    private void initView() {


    }
}
