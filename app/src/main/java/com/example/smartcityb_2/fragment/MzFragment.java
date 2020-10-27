package com.example.smartcityb_2.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.activity.AppMainActivity;
import com.example.smartcityb_2.activity.HosptialDetails;
import com.example.smartcityb_2.adapter.HosptialAdapter;
import com.example.smartcityb_2.bean.HospitalBean;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.OnClickItem;
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
 * @Create by 张瀛煜 on 2020/10/26 at 14:44
 */
public class MzFragment extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;
    private EditText etSearch;
    private Button btQuery;

    public MzFragment() {

    }

    private AppMainActivity appMainActivity;

    public MzFragment(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }

    public static MzFragment newInstance(AppMainActivity appMainActivity) {
        return new MzFragment(appMainActivity);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mzyy_fragment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("门诊预约");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appMainActivity.onBack();
            }
        });
        setVolley();
        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etSearch.getText())){
                    setVolley();
                }else {
                    List<HospitalBean> hospitalBeans1  = new ArrayList<>();
                    for (int i = 0; i < hospitalBeans.size(); i++) {
                        HospitalBean hospitalBean = hospitalBeans.get(i);
                        if (hospitalBean.getHospitalName().contains(etSearch.getText().toString())){
                            hospitalBeans1.add(hospitalBean);
                        }
                    }
                    HosptialAdapter adapter = new HosptialAdapter(getActivity(), hospitalBeans1);
                    listView.setAdapter(adapter);
                    adapter.setOnClickItem(new OnClickItem() {
                        @Override
                        public void onClick(int position, String name) {
                            HosptialDetails.newInstance(hospitalBeans.get(position), getActivity());
                        }
                    });
                }

            }
        });

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            etSearch.setText("");
        }
        super.onHiddenChanged(hidden);
    }

    List<HospitalBean> hospitalBeans;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("hospitalList")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hospitalBeans = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<HospitalBean>>() {
                                }.getType());
                        HosptialAdapter adapter = new HosptialAdapter(getActivity(), hospitalBeans);
                        listView.setAdapter(adapter);
                        adapter.setOnClickItem(new OnClickItem() {
                            @Override
                            public void onClick(int position, String name) {
                                HosptialDetails.newInstance(hospitalBeans.get(position), getActivity());
                            }
                        });

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
        listView = getView().findViewById(R.id.list_view);
        etSearch =getView(). findViewById(R.id.et_search);
        btQuery =getView(). findViewById(R.id.bt_query);
    }
}
