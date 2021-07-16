package com.leslie.recylerviewmodel.test.video;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_SETTLING;


/**
 * 作者：xjzhao
 * 时间：2021-07-17 00:21
 */
class MOnScrollListener extends RecyclerView.OnScrollListener {
    private int topMargin;

    public MOnScrollListener(int topMargin, RecyclerView recyclerView) {
        this.topMargin = topMargin;
        initPlay(recyclerView);
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        switch (newState){
            case SCROLL_STATE_IDLE:
                initPlay(recyclerView);
                break;
            case SCROLL_STATE_DRAGGING:
            case SCROLL_STATE_SETTLING:
                break;
        }
    }

    protected void initPlay(RecyclerView recyclerView){
        int count = recyclerView.getChildCount();
        for (int i = 0; i < count; i++){
            View child = recyclerView.getChildAt(i);
            StandardGSYVideoPlayer player;
            if (child instanceof ViewGroup && (null != (player = getVideoPlayer((ViewGroup) child))) && child.getTop() >= topMargin - 1){
                Object tag = player.getTag();
                player.startPlayLogic();
//                if (null != tag && tag instanceof Video) {
//                    player.seekTo(((Video) tag).getPosition());
//                }
                break;
            }
        }
    }

    private StandardGSYVideoPlayer getVideoPlayer(ViewGroup group){
        int count = group.getChildCount();
        for (int i = 0; i < count; i++){
            if (group.getChildAt(i) instanceof StandardGSYVideoPlayer){
                return (StandardGSYVideoPlayer) group.getChildAt(i);
            }
        }
        return null;
    }
}
