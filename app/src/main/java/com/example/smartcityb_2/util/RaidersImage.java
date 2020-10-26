package com.example.smartcityb_2.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.smartcityb_2.R;

import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 8:26
 */
public class RaidersImage extends androidx.appcompat.widget.AppCompatImageView {

    public RaidersImage(Context context) {
        super(context);
    }

    public RaidersImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RaidersImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int width, height;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, width, height),20, 20, Path.Direction.CCW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
