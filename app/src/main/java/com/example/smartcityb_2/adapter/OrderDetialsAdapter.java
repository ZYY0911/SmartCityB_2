package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.OrderDetials;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 10:01
 */
public class OrderDetialsAdapter extends ArrayAdapter<OrderDetials> {

    public OrderDetialsAdapter(@NonNull Context context, @NonNull List<OrderDetials> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_detial_item, parent, false);

        TextView itemSjmc = convertView.findViewById(R.id.item_sjmc);
        TextView itemSpmc = convertView.findViewById(R.id.item_spmc);
        TextView itemDj = convertView.findViewById(R.id.item_price);
        TextView itemSl = convertView.findViewById(R.id.item_sl);

        OrderDetials orderDetials = getItem(position);
        itemDj.setText(orderDetials.getPrice() + "");
        itemSl.setText(orderDetials.getCount());
        itemSjmc.setText(orderDetials.getBusiness());
        itemSpmc.setText(orderDetials.getCommodity());
        return convertView;
    }


    static class ViewHolder {

        private TextView itemSjmc;
        private TextView itemSpmc;
        private TextView itemDj;
        private TextView itemSl;
    }

    private void initView() {
    }
}
