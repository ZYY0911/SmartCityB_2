package com.example.smartcityb_2.util;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.internal.$Gson$Types;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 7:33
 */
public class Util {
    public static final String Row = "ROWS_DETAIL";

    public static void showDialog(String msg, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(msg);
        builder.setPositiveButton("确定", null);
        builder.show();
    }

    private static Toast toast;

    public static void showToast(String msg, Context context) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }


    public static String simpleDate(String type, Date date){
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }
}
