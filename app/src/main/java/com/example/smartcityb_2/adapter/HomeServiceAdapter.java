package com.example.smartcityb_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.bean.ServiceInfo;
import com.example.smartcityb_2.bean.ServiceOrder;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.CircleImage;
import com.example.smartcityb_2.util.MyNetImage2;
import com.example.smartcityb_2.util.OnClickItem;
import com.example.smartcityb_2.util.Util;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 8:20
 */
public class HomeServiceAdapter extends ArrayAdapter<ServiceOrder> {
    private MyNetImage2 itemImage;
    private TextView itemName;

    public HomeServiceAdapter(@NonNull Context context, @NonNull List<ServiceOrder> objects) {
        super(context, 0, objects);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.service_item, parent, false);
        final CircleImage itemImage = convertView.findViewById(R.id.item_image);
        final TextView itemName = convertView.findViewById(R.id.item_name);
        if (position == 9) {
            itemImage.setImageResource(R.mipmap.more_service);
            itemName.setText("更多服务");
        } else {
            ServiceOrder serviceOrder = getItem(position);
            VolleyTo volleyTo = new VolleyTo();
            volleyTo.setUrl("service_info")
                    .setJsonObject("serviceid", serviceOrder.getId())
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            ServiceInfo serviceInfo = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).optJSONObject(0).toString()
                                    , ServiceInfo.class);
                            itemImage.setImageUrl(serviceInfo.getIcon());
                            itemName.setText(serviceInfo.getServiceName());
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(position, itemName.getText().toString());

            }
        });
        return convertView;
    }

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public OnClickItem onClickItem;

    private void initView() {

    }
}
