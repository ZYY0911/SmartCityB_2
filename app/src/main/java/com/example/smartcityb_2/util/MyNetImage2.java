package com.example.smartcityb_2.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.net.VolleyImage;
import com.example.smartcityb_2.net.VolleyLoImage;

import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 8:13
 */
public class MyNetImage2 extends androidx.appcompat.widget.AppCompatImageView {
    public MyNetImage2(Context context) {
        super(context);
    }

    public MyNetImage2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNetImage2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setImageUrl(String path) {
        VolleyImage volleyImage = new VolleyImage();
        volleyImage.setUrl(path)
                .setVolleyLoImage(new VolleyLoImage() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        setImageBitmap(bitmap);
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }
}
