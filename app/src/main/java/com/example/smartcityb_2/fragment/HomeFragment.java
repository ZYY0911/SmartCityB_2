package com.example.smartcityb_2.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.activity.AppMainActivity;
import com.example.smartcityb_2.activity.EmptyActivity;
import com.example.smartcityb_2.activity.WebEmptyActivity;
import com.example.smartcityb_2.adapter.HomeServiceAdapter;
import com.example.smartcityb_2.adapter.NewAdapter;
import com.example.smartcityb_2.adapter.SubjectAdapter;
import com.example.smartcityb_2.bean.HomeImage;
import com.example.smartcityb_2.bean.NewsList;
import com.example.smartcityb_2.bean.ServiceOrder;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.MyGirdView;
import com.example.smartcityb_2.util.MyNetImage;
import com.example.smartcityb_2.util.OnClickItem;
import com.example.smartcityb_2.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 8:01
 */
public class HomeFragment extends Fragment {
    private ViewFlipper viewFlipper;
    private MyGirdView serviceGird;
    private MyGirdView subjectGird;
    private LinearLayout typeLayout;
    private ListView newsList;

    public HomeFragment() {

    }

    private AppMainActivity appMainActivity;

    public HomeFragment(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }

    public static HomeFragment newInstance(AppMainActivity appMainActivity) {
        return new HomeFragment(appMainActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_framgnet, container, false);
    }


    List<HomeImage> homeImages;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setVolley_Image();
        setVolley_Service();
        setVolley_Subject();
        setVolley_News();
    }

    List<NewsList> newsLists;

    private void setVolley_News() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getNEWsList")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        newsLists = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<NewsList>>() {
                                }.getType());
                        setNewType();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<String> newType;

    private void setNewType() {
        newType = new ArrayList<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllNewsType")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray(Util.Row);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            newType.add(jsonArray.optJSONObject(i).optString("newstype"));
                        }
                        for (int i = 0; i < newType.size(); i++) {
                            final TextView textView = new TextView(getActivity());
                            textView.setText(newType.get(i));
                            if (i == 0) {
                                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                                textView.setBackgroundResource(R.drawable.text_line);
                            } else {
                                textView.setTextColor(Color.parseColor("#333333"));
                                textView.setBackgroundResource(R.drawable.text_no_line);
                            }
                            textView.setPadding(0,0,0,10);
                            textView.setTextSize(20);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            layoutParams.setMargins(20, 0, 20, 0);
                            textView.setLayoutParams(layoutParams);
                            final int finalI = i;
                            textView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    for (int j = 0; j < typeLayout.getChildCount(); j++) {
                                        TextView textView1 = (TextView) typeLayout.getChildAt(j);
                                        if (j == finalI) {
                                            textView1.setTextColor(getResources().getColor(R.color.colorPrimary));
                                            textView1.setBackgroundResource(R.drawable.text_line);
                                        } else {
                                            textView1.setTextColor(Color.parseColor("#333333"));
                                            textView1.setBackgroundResource(R.drawable.text_no_line);
                                        }
                                    }
                                    setNewType(textView.getText().toString());
                                }
                            });
                            typeLayout.addView(textView);
                        }
                        setNewType(newType.get(0));

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void setNewType(String text) {
        final List<NewsList> newLists1 = new ArrayList<>();
        for (int i = 0; i < newsLists.size(); i++) {
            NewsList newList = newsLists.get(i);
            if (newList.getNewsType().equals(text)) {
                newLists1.add(newList);
            }
        }
        newsList.setAdapter(new NewAdapter(getActivity(), newLists1));
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                EmptyActivity.newInstance(newLists1.get(position).getTitle(), getActivity());
            }
        });
    }

    private void setVolley_Subject() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllSubject")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String arr = jsonObject.optString(Util.Row).replace("[", "").replace("]", "");
                        String[] arrs = arr.split(",");
                        final List<String> list = new ArrayList<>();
                        list.addAll(Arrays.asList(arrs));
                        subjectGird.setAdapter(new SubjectAdapter(getActivity(), list));
                        subjectGird.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                EmptyActivity.newInstance(list.get(position), getActivity());
                            }
                        });
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<ServiceOrder> serviceOrders;

    private void setVolley_Service() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getServiceInOrder")
                .setJsonObject("order", "2")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        serviceOrders = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<ServiceOrder>>() {
                                }.getType());
                        HomeServiceAdapter adapter = new HomeServiceAdapter(getActivity(), serviceOrders);
                        serviceGird.setAdapter(adapter);
                        adapter.setOnClickItem(new OnClickItem() {
                            @Override
                            public void onClick(int position, String name) {
                                if (name.equals("活动")) {
                                    appMainActivity.switchFragment(appMainActivity.map.get("活动"));
                                } else if (name.equals("违章查询")) {
                                    appMainActivity.switchFragment(appMainActivity.map.get("违章查询"));
                                } else if (name.equals("门诊预约")) {
                                    appMainActivity.switchFragment(appMainActivity.map.get("门诊预约"));
                                } else if (name.equals("全部服务")) {
                                    appMainActivity.switchFragment(appMainActivity.map.get("全部服务"));
                                } else {
                                    WebEmptyActivity.newInstance(serviceOrders.get(position).getId(),getActivity());
                                }
                            }
                        });

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley_Image() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getImages")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        homeImages = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<HomeImage>>() {
                                }.getType());
                        for (int i = 0; i < homeImages.size(); i++) {
                            MyNetImage image = new MyNetImage(getActivity());
                            image.setImageUrl(homeImages.get(i).getPath());
                            final int finalI = i;
                            image.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    EmptyActivity.newInstance("新闻" + (finalI + 1), getActivity());
                                }
                            });
                            viewFlipper.addView(image);
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initView() {
        viewFlipper = getView().findViewById(R.id.view_flipper);
        serviceGird = getView().findViewById(R.id.service_gird);
        subjectGird = getView().findViewById(R.id.subject_gird);
        typeLayout = getView().findViewById(R.id.type_layout);
        newsList = getView().findViewById(R.id.news_list);
    }
}
