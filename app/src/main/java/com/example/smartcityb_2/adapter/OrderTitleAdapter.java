package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.OrderTitle;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 9:50
 */
public class OrderTitleAdapter extends ArrayAdapter<OrderTitle> {
    public OrderTitleAdapter(@NonNull Context context, @NonNull List<OrderTitle> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_item, parent, false);
            holder = new ViewHolder();
            holder.itemDdh = convertView.findViewById(R.id.item_ddh);
            holder.itemLx = convertView.findViewById(R.id.item_lx);
            holder.itemRq = convertView.findViewById(R.id.item_rq);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        OrderTitle orderTitle = getItem(position);
        holder.itemDdh.setText(orderTitle.getId() + "");
        holder.itemLx.setText(orderTitle.getType());
        holder.itemRq.setText(orderTitle.getDate());
        return convertView;
    }

    static class ViewHolder {

        private TextView itemDdh;
        private TextView itemLx;
        private TextView itemRq;

    }

    private void initView() {
    }
}
