package com.example.smartcityb_2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.AppClient;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.activity.AppMainActivity;
import com.example.smartcityb_2.activity.ChoosePhoto;
import com.example.smartcityb_2.bean.UserInfo;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.MyNetImage2;
import com.example.smartcityb_2.util.Util;

import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 9:17
 */
public class MyInfoFragment extends Fragment {

    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private LinearLayout layoutInfo;
    private MyNetImage2 ivPhoto;
    private EditText etName;
    private EditText etSex;
    private EditText etTel;
    private EditText etIds;
    private Button btSave;

    public MyInfoFragment() {
    }

    private AppMainActivity appMainActivity;

    public MyInfoFragment(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }

    private Integer images[] = {R.mipmap.user1, R.mipmap.user2,
            R.mipmap.user3, R.mipmap.user4, R.mipmap.user5, R.mipmap.user6, R.mipmap.user7, R.mipmap.user8};

    public static MyInfoFragment newInstance(AppMainActivity appMainActivity) {
        return new MyInfoFragment(appMainActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_info_fragment, container, false);
    }


    UserInfo userInfo;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appMainActivity.switchFragment(appMainActivity.map.get("个人中心"));
            }
        });
        title.setText("个人信息");
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("setUserInfo")
                        //{userid:"1","name":"","avatar":"","phone":"phone","id":"1","gender":""}
                        .setJsonObject("userid", AppClient.getUserNum(AppClient.username))
                        .setJsonObject("name", etName.getText().toString())
                        .setJsonObject("avatar", layoutInfo.getTag() == null ? "" : layoutInfo.getTag().toString())
                        .setJsonObject("phone", etTel.getText().toString())
                        .setJsonObject("id", "")
                        .setJsonObject("gender", etSex.getText().toString())
                        .setDialog(getActivity())
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Util.showDialog("修改成功", getActivity());
                                } else {
                                    Util.showDialog("修改失败", getActivity());
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();
            }
        });
        setMsg();
        layoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChoosePhoto.class);
                intent.putExtra("info", userInfo);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            setMsg();
        }
        super.onHiddenChanged(hidden);
    }

    private String image;

    private void setMsg() {
        userInfo = ((MyCenterFragment) appMainActivity.map.get("个人中心")).userInfo;
        etTel.setText(userInfo.getPhone());
        etName.setText(userInfo.getName());
        etSex.setText(userInfo.getSex());
        StringBuilder builder = new StringBuilder(userInfo.getId());
        builder.replace(2, 14, "************");
        etIds.setText(builder.toString());
        image = userInfo.getAvatar().substring(userInfo.getAvatar().lastIndexOf("/"));
        layoutInfo.setTag(image);
        ivPhoto.setImageUrl(userInfo.getAvatar());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == getActivity().RESULT_OK) {
                ivPhoto.setImageResource(images[data.getIntExtra("index", 0)]);
                layoutInfo.setTag(data.getStringExtra("date"));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        layoutInfo = getView().findViewById(R.id.layout_info);
        ivPhoto = getView().findViewById(R.id.iv_photo);
        etName = getView().findViewById(R.id.et_name);
        etSex = getView().findViewById(R.id.et_sex);
        etTel = getView().findViewById(R.id.et_tel);
        etIds = getView().findViewById(R.id.et_ids);
        btSave = getView().findViewById(R.id.bt_save);
    }
}
