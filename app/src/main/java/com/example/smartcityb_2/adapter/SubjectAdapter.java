package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.util.RaidersImage;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 8:30
 */
public class SubjectAdapter extends ArrayAdapter<String> {
    private int images[] = {R.mipmap.c, R.mipmap.d, R.mipmap.a, R.mipmap.e};

    public SubjectAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.subject_item, parent, false);
            holder = new ViewHolder();
            holder.itemImage = convertView.findViewById(R.id.item_image);
            holder.itemName = convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemImage.setImageResource(images[position]);
        holder.itemName.setText(getItem(position));
        return convertView;
    }

    static class ViewHolder {

        private RaidersImage itemImage;
        private TextView itemName;
    }

    private void initView() {
    }
}
