package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.RlBean;
import com.example.smartcityb_2.util.OnClickItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/27 at 7:36
 */
public class RlAdapter extends ArrayAdapter<RlBean> {

    public RlAdapter(@NonNull Context context, @NonNull List<RlBean> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.rl_itme, parent, false);
        TextView itemText = convertView.findViewById(R.id.item_text);
        final RlBean rlBean = getItem(position);
        itemText.setText(rlBean.getDay() == 0 ? "" : rlBean.getDay() + "");
        switch (rlBean.getBg()) {
            case 3:
            case 1:
                itemText.setBackgroundResource(R.drawable.rl_12);
                break;
            case 0:
                itemText.setBackgroundResource(R.drawable.rl_1);
                break;
            case 2:
                itemText.setBackgroundResource(R.drawable.rl_3);
                break;
        }
        itemText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rlBean.getBg() != 3) {
                    Log.i("aaa", "onClick: "+rlBean.getBg());
                    onClickItem.onClick(position, "");
                }
            }
        });
        return convertView;
    }

    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    private void initView() {

    }
}
