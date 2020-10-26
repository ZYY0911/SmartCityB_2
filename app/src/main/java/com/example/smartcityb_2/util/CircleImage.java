package com.example.smartcityb_2.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.example.smartcityb_2.net.VolleyImage;
import com.example.smartcityb_2.net.VolleyLoImage;

import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 8:26
 */
public class CircleImage extends androidx.appcompat.widget.AppCompatImageView {

    public CircleImage(Context context) {
        super(context);
    }

    public CircleImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int width, height;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
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

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        path.addCircle(width / 2, height / 2, Math.min(width, height) / 2, Path.Direction.CCW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
