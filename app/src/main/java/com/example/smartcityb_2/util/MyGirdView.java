package com.example.smartcityb_2.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 8:06
 */
public class MyGirdView extends GridView {
    public MyGirdView(Context context) {
        super(context);
    }

    public MyGirdView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGirdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }
}
