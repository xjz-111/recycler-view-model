package com.leslie.recylerviewmodel;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;
import com.leslie.recylerviewmodel.test.Utils;

/**
 * 作者：xjzhao
 * 时间：2021-07-09 11:54
 */
public class MApplication extends Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();

        // 解决Glide加载图片设置tag的问题：You must not call setTag() on a view Glide is targeting
        ViewTarget.setTagId(R.id.glideIndexTag);

        instance = this;
        Utils.init();
    }

    public static Application getInstance() {
        return instance;
    }
}
