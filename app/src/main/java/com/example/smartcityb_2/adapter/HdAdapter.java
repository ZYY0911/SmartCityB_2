package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.HdDetails;
import com.example.smartcityb_2.util.MyNetImage2;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 10:38
 */
public class HdAdapter extends ArrayAdapter<HdDetails> {


    public HdAdapter(@NonNull Context context, @NonNull List<HdDetails> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
            holder = new ViewHolder();
            holder.itemImage = convertView.findViewById(R.id.item_image);
            holder.itemTitle = convertView.findViewById(R.id.item_title);
            holder.itemContext = convertView.findViewById(R.id.item_context);
            holder.itemMsg = convertView.findViewById(R.id.item_msg);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HdDetails hdDetails = getItem(position);
        holder.itemImage.setImageUrl(hdDetails.getImage());
        holder.itemTitle.setText(hdDetails.getName());
        holder.itemContext.setText("报名人数：" + hdDetails.getCount() + "\n点赞：" + hdDetails.getPraiseCount());
        holder.itemMsg.setGravity(Gravity.RIGHT);
        holder.itemMsg.setText("日期：" + hdDetails.getTime());
        return convertView;
    }

    static class ViewHolder {

        private MyNetImage2 itemImage;
        private TextView itemTitle;
        private TextView itemContext;
        private TextView itemMsg;
    }

    private void initView() {
    }
}
