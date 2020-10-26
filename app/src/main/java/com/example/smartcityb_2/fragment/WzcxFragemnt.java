package com.example.smartcityb_2.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.activity.AppMainActivity;
import com.example.smartcityb_2.activity.WzDetailsActivity;
import com.example.smartcityb_2.bean.CarType;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 11:39
 */
public class WzcxFragemnt extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private Spinner spType;
    private Spinner spCar;
    private EditText spNum;
    private Button btQuery;

    public WzcxFragemnt() {
    }

    private AppMainActivity appMainActivity;

    public WzcxFragemnt(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }


    public static WzcxFragemnt newInstance(AppMainActivity appMainActivity) {
        return new WzcxFragemnt(appMainActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wzccx_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("违章查询");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appMainActivity.onBack();
            }
        });
        setVolley_Type();
        setVolley_Car();

        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(spNum.getText())) {
                    Util.showDialog("车牌号不能为空", getActivity());
                    return;
                }
                String cp = spCar.getSelectedItem().toString() + spNum.getText().toString().trim().toUpperCase();
                if (cp.length() != 7) {
                    Util.showDialog("车牌号不合法", getActivity());
                    return;
                }
                WzDetailsActivity.newInstance(cp, getActivity());
            }
        });
    }

    List<CarType> carTypes2;

    private void setVolley_Car() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getCarPlace")
                .setDialog(getActivity())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        carTypes2 = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<CarType>>() {
                                }.getType());
                        List<String> strings = new ArrayList<>();
                        for (int i = 0; i < carTypes2.size(); i++) {
                            strings.add(carTypes2.get(i).getName());
                        }
                        spCar.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strings));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<CarType> carTypes;

    private void setVolley_Type() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllCarType")
                .setDialog(getActivity())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        carTypes = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<CarType>>() {
                                }.getType());
                        List<String> strings = new ArrayList<>();
                        for (int i = 0; i < carTypes.size(); i++) {
                            strings.add(carTypes.get(i).getName());
                        }
                        spType.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strings));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        spType = getView().findViewById(R.id.sp_type);
        spCar = getView().findViewById(R.id.sp_car);
        spNum = getView().findViewById(R.id.sp_num);
        btQuery = getView().findViewById(R.id.bt_query);
    }
}
