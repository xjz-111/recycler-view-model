package com.leslie.recylerviewmodel.test.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * 作者：xjzhao
 * 时间：2021-07-17 00:56
 */
public class MVideoView extends StandardGSYVideoPlayer {
    private OnProgressListener onProgressListener;

    public MVideoView(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public MVideoView(Context context) {
        super(context);
    }

    public MVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        super.onProgressChanged(seekBar, progress, fromUser);
        if (null != onProgressListener) onProgressListener.onProgress(progress);
    }


    interface OnProgressListener{
        void onProgress(int progress);
    }
}
