package com.example.smartcityb_2.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.net.VolleyImage;
import com.example.smartcityb_2.net.VolleyLoImage;

import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 8:13
 */
public class MyNetImage extends androidx.appcompat.widget.AppCompatImageView {
    public MyNetImage(Context context) {
        super(context);
        initView();
    }

    public MyNetImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyNetImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        setScaleType(ScaleType.FIT_XY);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
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
