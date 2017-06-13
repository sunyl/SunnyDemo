package com.widget.lib.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 按下50%透明，禁用20%透明， 包括文字 和 背景 , 没考虑CompoundDrawables
 */
public class AlphaTextView extends TextView {

    public AlphaTextView(Context context) {
        this(context, null);
    }

    public AlphaTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlphaTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setTextColor(int color) {
        int[][] status = new int[3][];
        status[0] = new int[] {-android.R.attr.state_enabled};
        status[1] = new int[] {android.R.attr.state_pressed};
        status[2] = new int[] {};

        int color2 = makeColorAlpha(color, 128);
        int color3 = makeColorAlpha(color, 110);

        int[] colors = new int[] {color3, color2, color};
        ColorStateList list = new ColorStateList(status, colors);
        super.setTextColor(list);
    }

    /**
     * 由于会attr里是将颜色设置到ColorStateList，所以需要重写该方法
     * @param colors
     */
    @Override
    public void setTextColor(ColorStateList colors) {
        if(!colors.isStateful()) {
            setTextColor(colors.getDefaultColor());
        } else {
            super.setTextColor(colors);
        }
    }

    @Override
    public void refreshDrawableState() {
        super.refreshDrawableState();

        int[] stateList = getDrawableState();
        boolean pressed = false;
        boolean enable = false;
        for(int state : stateList) {
            if(state == android.R.attr.state_pressed) {
                pressed = true;
            } else if(state == android.R.attr.state_enabled) {
                enable = true;
            }
        }

        Drawable back = getBackground();
        if(enable) {
            if(back != null) {
                back.setAlpha(pressed ? 128 : 255);
            }
        } else {
            if(back != null) {
                back.setAlpha(110);
            }
        }
    }

    private static int makeColorAlpha(int color, int alpha) {
        alpha += alpha >> 7;   // make it 0..256
        final int baseAlpha = color >>> 24;
        final int useAlpha = baseAlpha * alpha >> 8;
        final int useColor = (color << 8 >>> 8) | (useAlpha << 24);
        return useColor;
    }

}