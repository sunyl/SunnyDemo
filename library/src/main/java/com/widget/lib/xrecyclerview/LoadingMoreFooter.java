package com.widget.lib.xrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.widget.sun.lib.R;
import com.widget.lib.utils.Res;

public class LoadingMoreFooter extends LinearLayout {

    private ImageView mImageViewLoading;
    public final static int STATE_LOADING = 0;
    public final static int STATE_COMPLETE = 1;
    public final static int STATE_NOMORE = 2;
    private TextView mText;
    private String loadingHint;
    private String noMoreHint;
    private String loadingDoneHint;

    public LoadingMoreFooter(Context context) {
        super(context);
        initView();
    }

    public LoadingMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void setLoadingHint(String hint) {
        loadingHint = hint;
    }

    public void setNoMoreHint(String hint) {
        noMoreHint = hint;
    }

    public void setLoadingDoneHint(String hint) {
        loadingDoneHint = hint;
    }

    public void initView() {
        setGravity(Gravity.CENTER);
        setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mImageViewLoading = new ImageView(getContext());
        mImageViewLoading.setImageResource(R.drawable.bg_refresh_loding);
        mImageViewLoading.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mImageViewLoading);

        loadingHint = Res.getString(R.string.refresh_loading);
        noMoreHint = Res.getString(R.string.refresh_no_more_data);
        mText = new TextView(getContext());
        mText.setTextColor(Res.getColor(R.color.xrecyclerview_style_color));
        mText.setTextSize(12);
        mText.setText(loadingHint);
        loadingDoneHint = (String) getContext().getText(R.string.loading_done);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins((int) getResources().getDimension(R.dimen.textandiconmargin), 0, 0, 0);
        mText.setLayoutParams(layoutParams);
        addView(mText);
    }

    public void setState(int state) {
        switch (state) {
            case STATE_LOADING:
                mImageViewLoading.setVisibility(View.VISIBLE);
                mText.setText(loadingHint);
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_COMPLETE:
                mText.setText(loadingDoneHint);
                this.setVisibility(View.GONE);
                break;
            case STATE_NOMORE:
                mText.setText(noMoreHint);
                mImageViewLoading.setVisibility(View.GONE);
                this.setVisibility(View.VISIBLE);
                break;
        }
    }
}
