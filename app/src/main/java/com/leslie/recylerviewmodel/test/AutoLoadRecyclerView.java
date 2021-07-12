package com.leslie.recylerviewmodel.test;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

/**
 * 作者：xjzhao
 * 时间：2021-07-08 13:57
 */
public class AutoLoadRecyclerView extends RecyclerView {
    public AutoLoadRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public AutoLoadRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoLoadRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addOnScrollListener(new AutoScrollerListener());
    }




    private class AutoScrollerListener extends RecyclerView.OnScrollListener{

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState){
                case SCROLL_STATE_IDLE:
                    Glide.with(getContext()).resumeRequests();
                    break;
                case SCROLL_STATE_DRAGGING:
                case SCROLL_STATE_SETTLING:
                    Glide.with(getContext()).pauseRequests();
                    break;
            }
        }
    }
}
