package com.widget.lib.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 按下50%透明，禁用20%透明
 */
public class AlphaImageView extends ImageView {

    public AlphaImageView(Context context) {
        this(context, null);
    }

    public AlphaImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlphaImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

//    protected void dispatchSetPressed(boolean pressed) {
//        super.dispatchSetPressed(pressed);
//        Drawable back = getBackground();
//        Drawable front = getDrawable();
//        if(front != null) {
//            front.setAlpha(pressed ? 128 : 255);
//        }
//        if(back != null) {
//            back.setAlpha(pressed ? 128 : 255);
//        }
//    }

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
        Drawable front = getDrawable();
        if(enable) {
            if(front != null) {
                front.setAlpha(pressed ? 128 : 255);
            }
            if(back != null) {
                back.setAlpha(pressed ? 128 : 255);
            }
        } else {
            if(front != null) {
                front.setAlpha(110);
            }
            if(back != null) {
                back.setAlpha(110);
            }
        }
    }
}