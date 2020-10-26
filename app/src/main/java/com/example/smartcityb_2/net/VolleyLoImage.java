package com.example.smartcityb_2.net;

import android.graphics.Bitmap;

import com.android.volley.VolleyError;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 8:12
 */
public interface VolleyLoImage  {
    void onResponse(Bitmap bitmap);
    void onErrorResponse(VolleyError volleyError);
}
