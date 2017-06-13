package com.widget.sun.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.widget.lib.adapter.EasyAdapter;
import com.widget.lib.utils.ViewHolder;
import com.widget.sun.demo.R;
import com.widget.sun.demo.entity.MoveSubject;

import java.util.ArrayList;

/**
 * created by sunyunlong at 2017/5/23
 */
public class RecyclerViewAdapter extends EasyAdapter<MoveSubject> {

    public RecyclerViewAdapter(Context context, ArrayList<MoveSubject> data) {
        super(context, data, R.layout.lvi_movie);
    }

    @Override
    public void updateItem(RecyclerView.ViewHolder holder, final MoveSubject subjectsBean, final int position) {

        ImageView iv_pic = ViewHolder.get(holder.itemView, R.id.iv_movie_pic);
        TextView moveTitle = ViewHolder.get(holder.itemView, R.id.tv_movie_title);
        TextView movieDoc = ViewHolder.get(holder.itemView, R.id.tv_movie_doc);
        TextView movieArt = ViewHolder.get(holder.itemView, R.id.tv_movie_art);
        TextView movieType = ViewHolder.get(holder.itemView, R.id.tv_movie_type);

        TextView movieGrade = ViewHolder.get(holder.itemView, R.id.tv_movie_grade);
        String title = (position + 1) + "、" + subjectsBean.getTitle() + "/" + subjectsBean.getOriginal_title();
        moveTitle.setText(title);
        String doc = "";
        for (MoveSubject.Cast cast : subjectsBean.getDirectors()) {
            doc += cast.getName() + "  ";
        }
        movieDoc.setText("导演:" + doc);
        String casts = "";
        for (MoveSubject.Cast castsBean : subjectsBean.getCasts()) {
            casts += castsBean.getName() + "  ";
        }

        movieArt.setText("主演:" + casts);
        String genres = "";
        for (String genre : subjectsBean.getGenres()) {
            genres += genre + " ";
        }
        movieType.setText(subjectsBean.getYear() + " / " + genres);
        movieGrade.setText(subjectsBean.getRating().getAverage() + "");
        Glide.with(mContext)
                .load(subjectsBean.getImages().getSmall())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_pic);

    }

}
