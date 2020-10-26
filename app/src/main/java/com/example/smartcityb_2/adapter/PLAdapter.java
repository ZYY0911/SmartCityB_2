package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.AppClient;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.Hdpl;
import com.example.smartcityb_2.bean.UserInfo;
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
 * @Create by 张瀛煜 on 2020/10/26 at 10:58
 */
public class PLAdapter extends ArrayAdapter<Hdpl> {

    public PLAdapter(@NonNull Context context, @NonNull List<Hdpl> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.pl_item, parent, false);
        final MyNetImage2 itemImage = convertView.findViewById(R.id.item_image);
        TextView itemTitle = convertView.findViewById(R.id.item_title);
        TextView itemContext = convertView.findViewById(R.id.item_context);
        TextView itemMsg = convertView.findViewById(R.id.item_msg);
        Hdpl hdpl = getItem(position);
        itemTitle.setText(hdpl.getUsername());
        itemContext.setText(hdpl.getCommitContent());
        itemMsg.setText(hdpl.getCommitTime());
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUserInfo")
                .setJsonObject("userid", AppClient.getUserNum(hdpl.getUsername()))
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        UserInfo userInfo = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).optJSONObject(0).toString()
                                , UserInfo.class);
                        itemImage.setImageUrl(userInfo.getAvatar());
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        return convertView;
    }


}
