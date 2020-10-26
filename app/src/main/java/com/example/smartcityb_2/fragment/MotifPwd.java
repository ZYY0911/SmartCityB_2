package com.example.smartcityb_2.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.AppClient;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.activity.AppMainActivity;
import com.example.smartcityb_2.activity.YjfkActivity;
import com.example.smartcityb_2.bean.UserInfo;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.Util;

import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 10:16
 */
public class MotifPwd extends Fragment {
    private AppMainActivity appMainActivity;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText etPwd;
    private EditText etNewPwd;
    private EditText etNewPwd2;
    private Button btSave;

    public MotifPwd(AppMainActivity appMainActivity) {
        this.appMainActivity = appMainActivity;
    }

    public MotifPwd() {
    }

    public static MotifPwd newInstance(AppMainActivity appMainActivity) {
        return new MotifPwd(appMainActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.motif_pwd, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("修改密码");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appMainActivity.switchFragment(appMainActivity.map.get("个人中心"));
            }
        });
        setView();
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etPwd.getText()) || TextUtils.isEmpty(etNewPwd.getText())) {
                    Util.showDialog("输入内容不能为空", getActivity());
                    return;
                }
                String oldpwd1, newPwd, newPwd1;
                oldpwd1 = etPwd.getText().toString();
                newPwd = etNewPwd.getText().toString();
                newPwd1 = etNewPwd2.getText().toString();
                if (!oldpwd1.equals(oldPwd)) {
                    Util.showDialog("旧密码不正确", getActivity());
                    return;
                }
                if (!newPwd.equals(newPwd1)) {
                    Util.showDialog("两次密码不一致", getActivity());
                    return;
                }
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("setPwd")
                        //{userid:"1",password:"123"}
                        .setDialog(getActivity())
                        .setJsonObject("userid", AppClient.getUserNum(AppClient.username))
                        .setJsonObject("password", newPwd)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Util.showToast("修改成功", getActivity());
                                    setView();
                                } else {
                                    Util.showToast("修改成功", getActivity());
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Util.showToast("修改成功", getActivity());
                            }
                        }).start();
            }
        });
    }

    private String oldPwd;

    private void setView() {
        etNewPwd.setText("");
        etNewPwd2.setText("");
        etPwd.setText("");
        setPwd();
    }

    private void setPwd() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getPwd")
                .setJsonObject("userid", AppClient.getUserNum(AppClient.username))
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        oldPwd = jsonObject.optString("password");
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            setView();

        }
        super.onHiddenChanged(hidden);
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        etPwd = getView().findViewById(R.id.et_pwd);
        etNewPwd = getView().findViewById(R.id.et_new_pwd);
        etNewPwd2 = getView().findViewById(R.id.et_new_pwd2);
        btSave = getView().findViewById(R.id.bt_save);
    }
}
