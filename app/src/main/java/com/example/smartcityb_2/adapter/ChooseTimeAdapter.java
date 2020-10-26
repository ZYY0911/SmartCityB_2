package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.ChooseTime;
import com.example.smartcityb_2.util.OnClickItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 15:47
 */
public class ChooseTimeAdapter extends ArrayAdapter<ChooseTime> {

    private String name;

    public ChooseTimeAdapter(@NonNull Context context, @NonNull List<ChooseTime> objects, String name) {
        super(context, 0, objects);
        this.name = name;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.choose_item, parent, false);
            holder = new ViewHolder();
            holder.itemName = convertView.findViewById(R.id.item_name);
            holder.itemBt = convertView.findViewById(R.id.item_bt);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /**
         * num : 126
         * hospitalId : 3
         * departmentId : 1
         * time : 2020-10-3 周六 下午14：00
         * type : 普通门诊
         * doctorId : 21
         */
        ChooseTime chooseTime = getItem(position);
        holder.itemName.setText(chooseTime.getTime() + "," + name);
        holder.itemBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(position, "");
            }
        });
        return convertView;
    }

    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    static class ViewHolder {

        private TextView itemName;
        private Button itemBt;
    }

    private void initView() {
    }
}
