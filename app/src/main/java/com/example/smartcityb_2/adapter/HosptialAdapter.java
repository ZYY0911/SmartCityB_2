package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.HospitalBean;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.MyNetImage2;
import com.example.smartcityb_2.util.OnClickItem;
import com.example.smartcityb_2.util.Util;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 14:54
 */
public class HosptialAdapter extends ArrayAdapter<HospitalBean> {

    public HosptialAdapter(@NonNull Context context, @NonNull List<HospitalBean> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hosptial_item, parent, false);
            holder = new ViewHolder();
            holder.itemImage = convertView.findViewById(R.id.item_image);
            holder.itemName = convertView.findViewById(R.id.item_name);
            holder.ratingBar = convertView.findViewById(R.id.rating_bar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HospitalBean hospitalBean = getItem(position);
        holder.itemImage.setImageUrl(hospitalBean.getPicture());
        holder.itemName.setText(hospitalBean.getHospitalName());
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getRankByHospitalId")
                .setJsonObject("hospitalId", hospitalBean.getHospitalId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONObject jsonObject1 = jsonObject.optJSONArray(Util.Row).optJSONObject(0);
                        Log.i("aaa", "onResponse: "+Float.parseFloat(jsonObject1.optString("rank")));
                        holder.ratingBar.setRating(Float.parseFloat(jsonObject1.optString("rank")));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(position,"");
            }
        });
        return convertView;
    }


    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    static class ViewHolder {

        private MyNetImage2 itemImage;
        private TextView itemName;
        private RatingBar ratingBar;
    }

    private void initView() {
    }
}
