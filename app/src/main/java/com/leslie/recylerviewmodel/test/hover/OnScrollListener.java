package com.leslie.recylerviewmodel.test.hover;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者：xjzhao
 * 时间：2021-07-09 22:31
 */
public class OnScrollListener extends RecyclerView.OnScrollListener {


    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        Log.i("xjzhao", "dy : " + dy);
    }
}
