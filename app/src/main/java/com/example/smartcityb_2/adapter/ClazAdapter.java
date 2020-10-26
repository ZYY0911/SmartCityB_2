package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.Clwz;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 14:23
 */
public class ClazAdapter extends ArrayAdapter<Clwz> {

    public ClazAdapter(@NonNull Context context, @NonNull List<Clwz> objects,int myCount) {
        super(context, 0, objects);
        this.myCount =myCount;
    }

    public void setMyCount(int myCount) {
        this.myCount = myCount;
    }

    private int myCount;
    @Override
    public int getCount() {
        return myCount;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.clwz_item, parent, false);
            holder = new ViewHolder();
            holder.itemCp = convertView.findViewById(R.id.item_cp);
            holder.itemSj = convertView.findViewById(R.id.item_sj);
            holder.itemDl = convertView.findViewById(R.id.item_dl);
            holder.itemFk = convertView.findViewById(R.id.item_fk);
            holder.itemJf = convertView.findViewById(R.id.item_jf);
            holder.itemZt = convertView.findViewById(R.id.item_zt);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Clwz clwz = getItem(position);
        holder.itemCp.setText("车牌号：" + clwz.getCarid());
        holder.itemSj.setText("违法时间：" + clwz.getTime());
        holder.itemDl.setText("违章地点：" + clwz.getPlace());
        holder.itemFk.setText("罚款：" + clwz.getCost());
        holder.itemJf.setText("违章记分：" + clwz.getDeductPoints());
        holder.itemZt.setText("处理状态：" + (clwz.getStatus() == 0 ? "未处理" : "已处理"));

        return convertView;
    }


    static class ViewHolder {

        private TextView itemCp;
        private TextView itemSj;
        private TextView itemDl;
        private TextView itemFk;
        private TextView itemJf;
        private TextView itemZt;
    }

    private void initView() {

    }
}
