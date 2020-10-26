package com.example.smartcityb_2.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.activity.AppMainActivity;
import com.example.smartcityb_2.activity.EmptyActivity;
import com.example.smartcityb_2.activity.HdDetailsActivity;
import com.example.smartcityb_2.adapter.HdAdapter;
import com.example.smartcityb_2.bean.Hd;
import com.example.smartcityb_2.bean.HdDetails;
import com.example.smartcityb_2.bean.HdType;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.MyNetImage;
import com.example.smartcityb_2.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 10:27
 */
public class HdFragment extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ViewFlipper viewFlipper;
    private LinearLayout typeLayout;
    private ListView newsList;

    public HdFragment() {
    }

    private AppMainActivity appMainActivity;

    public HdFragment(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }

    public static HdFragment newInstance(AppMainActivity appMainActivity) {
        return new HdFragment(appMainActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hd_layout, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("活动");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appMainActivity.onBack();
            }
        });
        setVolley_image();
        setVolley_Type();
    }


    List<HdType> hdTypes;

    private void setVolley_Type() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllActionType")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hdTypes = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<HdType>>() {
                                }.getType());
                        for (int i = 0; i < hdTypes.size(); i++) {
                            final TextView textView = new TextView(getActivity());
                            textView.setText(hdTypes.get(i).getTypename());
                            if (i == 0) {
                                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                                textView.setBackgroundResource(R.drawable.text_line);
                            } else {
                                textView.setTextColor(Color.parseColor("#333333"));
                                textView.setBackgroundResource(R.drawable.text_no_line);
                            }
                            textView.setTag(hdTypes.get(i).getId());
                            textView.setTextSize(20);
                            textView.setPadding(0,0,0,10);
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
                                    setNewType(textView.getTag().toString());
                                }
                            });
                            typeLayout.addView(textView);
                        }
                        setNewType(hdTypes.get(0).getId() + "");
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }


    private boolean isFiarst = true;

    @Override
    public void onResume() {
        if (isFiarst) {
            isFiarst = false;
        } else {
            for (int i = 0; i < typeLayout.getChildCount(); i++) {
                TextView textView = (TextView) typeLayout.getChildAt(i);
                if (i == 0) {
                    textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    textView.setBackgroundResource(R.drawable.text_line);
                } else {
                    textView.setTextColor(Color.parseColor("#333333"));
                    textView.setBackgroundResource(R.drawable.text_no_line);
                }
            }
            setNewType(hdTypes.get(0).getId() + "");
        }
        super.onResume();
    }

    List<HdDetails> hdDetails;

    private void setNewType(String toString) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getActionsByType")
                .setJsonObject("typeid", toString)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hdDetails = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<HdDetails>>() {
                                }.getType());
                        Collections.sort(hdDetails, new Comparator<HdDetails>() {
                            @Override
                            public int compare(HdDetails o1, HdDetails o2) {
                                Date date1 = null, date2 = null;
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                try {
                                    date1 = format.parse(o1.getTime());
                                    date2 = format.parse(o2.getTime());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                return date2.compareTo(date1);
                            }
                        });
                        newsList.setAdapter(new HdAdapter(getActivity(), hdDetails));
                        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                HdDetailsActivity.newInstance(hdDetails.get(position), getActivity());
                            }
                        });
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }


    List<Hd> hds;

    private void setVolley_image() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getActionImages")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hds = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).toString()
                                , new TypeToken<List<Hd>>() {
                                }.getType());
                        for (int i = 0; i < hds.size(); i++) {
                            MyNetImage image = new MyNetImage(getActivity());
                            image.setImageUrl(hds.get(i).getImage());
                            final int finalI = i;
                            image.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    VolleyTo volleyTo1 = new VolleyTo();
                                    volleyTo1.setUrl("getActionById")
                                            .setJsonObject("id", hds.get(finalI).getId())
                                            .setVolleyLo(new VolleyLo() {
                                                @Override
                                                public void onResponse(JSONObject jsonObject) {
                                                    HdDetails hdDetails = new Gson().fromJson(jsonObject.optJSONArray(Util.Row).optJSONObject(0).toString()
                                                            , HdDetails.class);
                                                    HdDetailsActivity.newInstance(hdDetails, getActivity());
                                                }

                                                @Override
                                                public void onErrorResponse(VolleyError volleyError) {

                                                }
                                            }).start();
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
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        viewFlipper = getView().findViewById(R.id.view_flipper);
        typeLayout = getView().findViewById(R.id.type_layout);
        newsList = getView().findViewById(R.id.news_list);
    }
}

