package com.example.smartcityb_2.dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.AppClient;
import com.example.smartcityb_2.R;
import com.example.smartcityb_2.activity.YjfkActivity;
import com.example.smartcityb_2.net.VolleyLo;
import com.example.smartcityb_2.net.VolleyTo;
import com.example.smartcityb_2.util.OnClickItem;
import com.example.smartcityb_2.util.Util;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONObject;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 11:15
 */
public class PlDialog extends BottomSheetDialogFragment {
    private TextView tvExit;
    private TextView tvSubmit;
    private EditText etPl;
    private int id;

    public PlDialog(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pl_dilaog, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        tvSubmit.setTag(1);
        etPl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    tvSubmit.setTextColor(Color.parseColor("#666666"));
                    tvSubmit.setTag(1);
                } else {
                    tvSubmit.setTextColor(Color.RED);
                    tvSubmit.setTag(2);
                }
            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvSubmit.getTag().equals(2)) {
                    VolleyTo volleyTo = new VolleyTo();
                    volleyTo.setUrl("publicActionCommit")
                            //{"id":"2","userid":"4","commitTime":"2020-10-3 10:00:00","commitContent":"666"}
                            .setJsonObject("id", id)
                            .setJsonObject("userid", AppClient.username)
                            .setJsonObject("commitTime", Util.simpleDate("yyyy-MM-dd HH:mm:ss", new Date()))
                            .setJsonObject("commitContent", etPl.getText().toString())
                            .setVolleyLo(new VolleyLo() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    if (jsonObject.optString("RESULT").equals("S")) {
                                        Util.showToast("评论成功", getActivity());
                                        onClickItem.onClick(1,"");
                                    } else {
                                        Util.showToast("评论失败", getActivity());
                                        onClickItem.onClick(2,"");
                                    }
                                    getDialog().dismiss();
                                }

                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Util.showToast("评论失败", getActivity());
                                    onClickItem.onClick(2,"");
                                    getDialog().dismiss();

                                }
                            }).start();
                }
            }
        });
        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }
    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    private void initView() {
        tvExit = getView().findViewById(R.id.tv_exit);
        tvSubmit = getView().findViewById(R.id.tv_submit);
        etPl = getView().findViewById(R.id.et_pl);
    }
}
