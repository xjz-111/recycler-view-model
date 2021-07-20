package com.leslie.recylerviewmodel.test;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * 作者：xjzhao
 * 时间：2021-07-09 01:00
 */
public class DataBindingUtils {

    /**
     *
     * @param tv
     * @param color
     */
    @BindingAdapter({"android:textColor"})
    public static void setTextColor(TextView tv, String color){
        if (null != tv && !TextUtils.isEmpty(color)) {
            tv.setTextColor(Color.parseColor(color));
        }
    }

    /**
     * 列表上加载图片
     * @param img
     * @param url
     * @param res
     */
    @BindingAdapter({"url", "placeholder"})
    public static void loadImg(ImageView img, String url, Drawable res){
        if (null != img) {
            Object tag = img.getTag();
            if (null == tag || tag != url){
                loadImg(img, url, res, true);
                img.setTag(url);
            }
        }
    }

    /**
     * 列表上加载图片
     * @param img
     * @param url
     * @param res
     */
    @BindingAdapter({"url", "placeholder", "w", "h"})
    public static void loadImg(ImageView img, String url, Drawable res, int w, int h){
        if (null != img) {
            Object tag = img.getTag();
            if (null == tag || tag != url){
                ViewGroup.LayoutParams lp = img.getLayoutParams();
                lp.width = w;
                lp.height = h;
                loadImg(img, url, res, true);
                img.setTag(url);
            }
        }
    }


    static void loadImg(ImageView img, String url, Drawable placeholder, boolean cache) {
        if (isGif(url)) {
            Glide.with(img.getContext())
                    .load(url)
                    .asGif()
                    .placeholder(placeholder)
                    .diskCacheStrategy(cache ? DiskCacheStrategy.SOURCE : DiskCacheStrategy.NONE)
                    .fitCenter()
                    .into(img);
        } else {
            Glide.with(img.getContext())
                    .load(url)
                    .asBitmap()
                    .centerCrop()
                    .placeholder(placeholder)
                    .diskCacheStrategy(cache ? DiskCacheStrategy.ALL : DiskCacheStrategy.NONE)
                    .dontAnimate()
                    .into(img);
        }
    }

    static boolean isGif(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }

        return url.endsWith(".gif") || url.endsWith(".GIF");

    }
}

