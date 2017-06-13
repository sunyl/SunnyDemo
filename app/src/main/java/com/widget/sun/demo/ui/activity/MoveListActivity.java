package com.widget.sun.demo.ui.activity;

import android.os.Bundle;

import com.widget.lib.adapter.EasyAdapter;
import com.widget.lib.model.BaseModel;
import com.widget.lib.presenter.BaseListPresent;
import com.widget.lib.widget.LoadingListLayout;
import com.widget.sun.demo.R;
import com.widget.sun.demo.adapter.RecyclerViewAdapter;
import com.widget.sun.demo.entity.MoveSubject;
import com.widget.sun.demo.model.MoviesListModel;
import com.widget.sun.demo.presenter.MoviesListPresent;
import com.widget.sun.demo.ui.base.BaseActivity;
import com.widget.sun.demo.utils.OpenUtils;

import java.util.ArrayList;

import butterknife.OnClick;


public class MoveListActivity extends BaseActivity {

    private RecyclerViewAdapter mAdapter;
    private ArrayList<MoveSubject> mListData = new ArrayList<>();
    private BaseListPresent mMoviesListPresent;
    private BaseModel mMoviesListModel;
    private LoadingListLayout<MoveSubject> mLayout;

    @Override
    public int getLayoutResId() {
        return R.layout.layout_activity_movelist;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        mMoviesListModel = new MoviesListModel();
        mMoviesListPresent = new MoviesListPresent(mMoviesListModel);
        mAdapter = new RecyclerViewAdapter(MoveListActivity.this, mListData);
        mLayout = new LoadingListLayout<>(mRoot);
        mLayout.setData(mListData);
        mLayout.setOnlyOnePage(false);
        mLayout.setAdapter(mAdapter);
        mLayout.setPresent(mMoviesListPresent);
        mLayout.init();

        mAdapter.setOnItemClickListener(new EasyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                OpenUtils.openMoveDetailsActivity(mSwipeBackHelper);
            }
        });
    }

    @OnClick(R.id.iv_title_back)
    void onClickBack() {
        mSwipeBackHelper.backward();
    }

}
