package com.example.smartcityb_2.dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smartcityb_2.R;
import com.example.smartcityb_2.util.Util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 16:44
 */
public class YyDialog extends DialogFragment {
    private EditText etIp;
    private TextView etPort;
    private EditText etJb;
    private Button btSubmit;
    private Button btExit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yy_dialog, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.showToast("预约成功", getContext());
                getDialog().dismiss();
            }
        });
        etPort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etPort.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, 2020, 9, 26);
                datePickerDialog.show();
            }
        });
    }

    private void initView() {
        etIp = getView().findViewById(R.id.et_ip);
        etPort = getView().findViewById(R.id.et_port);
        etJb = getView().findViewById(R.id.et_jb);
        btSubmit = getView().findViewById(R.id.bt_submit);
        btExit = getView().findViewById(R.id.bt_exit);
    }
}
