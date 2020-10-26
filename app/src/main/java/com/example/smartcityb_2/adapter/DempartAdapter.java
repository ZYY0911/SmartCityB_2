package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.DempartList;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 15:39
 */
public class DempartAdapter extends ArrayAdapter<DempartList> {

    public DempartAdapter(@NonNull Context context, @NonNull List<DempartList> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.dempar_item, parent, false);
        TextView itemName = convertView.findViewById(R.id.item_name);
        itemName.setText(getItem(position).getDeptName());
        return convertView;
    }

    static class ViewHolder {
    }

    private void initView() {

    }
}
