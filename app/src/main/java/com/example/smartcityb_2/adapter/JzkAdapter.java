package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.Jzk;
import com.example.smartcityb_2.util.OnClickItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 15:18
 */
public class JzkAdapter extends ArrayAdapter<Jzk> {

    public JzkAdapter(@NonNull Context context, @NonNull List<Jzk> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.jzk_item, parent, false);
            holder = new ViewHolder();
            holder.itemName = convertView.findViewById(R.id.item_name);
            holder.itemSex = convertView.findViewById(R.id.item_sex);
            holder.itemID = convertView.findViewById(R.id.item_ID);
            holder.itemTel = convertView.findViewById(R.id.item_tel);
            holder.itemAddress = convertView.findViewById(R.id.item_address);
            holder.itemBirth = convertView.findViewById(R.id.item_birth);
            holder.itemLeft = convertView.findViewById(R.id.item_left);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Jzk jzk = getItem(position);
        holder.itemName.setText("姓名：" + jzk.getName());
        holder.itemID.setText("身份证号：" + jzk.getID());
        holder.itemSex.setText("性别：" + jzk.getSex());
        holder.itemAddress.setText("地址：" + jzk.getAddress());
        holder.itemTel.setText("手机：" + jzk.getTel());
        holder.itemBirth.setText("出生日期：" + jzk.getBirthday());
        holder.itemLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(position, "1");

            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(position, "2");
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
        private TextView itemSex;
        private TextView itemID;
        private TextView itemTel;
        private TextView itemAddress;
        private ImageView itemLeft;
        private TextView itemBirth;
    }

    private void initView() {

    }
}
