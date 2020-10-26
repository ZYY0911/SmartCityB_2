package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.NewDetails;
import com.example.smartcityb_2.bean.NewsList;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.MyNetImage2;
import com.example.smartcityb_2.util.Util;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 8:41
 */
public class NewAdapter extends ArrayAdapter<NewsList> {

    public NewAdapter(@NonNull Context context, @NonNull List<NewsList> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        MyNetImage2 itemImage = convertView.findViewById(R.id.item_image);
        TextView itemTitle = convertView.findViewById(R.id.item_title);
        TextView itemContext = convertView.findViewById(R.id.item_context);
        final TextView itemMsg = convertView.findViewById(R.id.item_msg);
        NewsList newsList = getItem(position);
        itemImage.setImageUrl(newsList.getPicture());
        itemTitle.setText(newsList.getTitle());
        itemContext.setText(newsList.getAbstractX());
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getNewsInfoById")
                .setJsonObject("newsid", newsList.getNewsid())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        NewDetails newDetails = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).optJSONObject(0).toString()
                                , NewDetails.class);
                        itemMsg.setText("评论：" + newDetails.getAudienceCount() + " 日期：" + newDetails.getPublicTime());
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        return convertView;
    }

    private void initView() {


    }
}
