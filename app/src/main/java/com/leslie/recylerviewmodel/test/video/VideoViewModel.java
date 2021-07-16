package com.leslie.recylerviewmodel.test.video;

import android.view.View;

import androidx.annotation.NonNull;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.VideoVmBinding;
import com.leslie.recylerviewmodel.test.data.Video;

import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-16 21:37
 */
public class VideoViewModel extends BaseCommonViewModel<Video, VideoVmBinding> {

    public VideoViewModel(List<Video> list) {
        super(list);
    }

    @Override
    protected void initWhitContext() {
        super.initWhitContext();
        getRecyclerView().addOnScrollListener(new MOnScrollListener(0, getRecyclerView()));
    }

    @Override
    public int getVariable(Video video, int position) {
        return BR.video;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.video_vm;
    }

    @Override
    protected void initView(@NonNull VideoVmBinding binding, @NonNull Video video, int position, List<Object> payloads) {
        super.initView(binding, video, position, payloads);

        binding.player.setUpLazy(video.getUrl(), true, null, null, "");
        //增加title
        binding.player.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        binding.player.getBackButton().setVisibility(View.GONE);
        //设置全屏按键功能
        binding.player.getFullscreenButton().setOnClickListener(v -> binding.player.startWindowFullscreen(context, false, true));
        //防止错位设置
        binding.player.setPlayTag(video.getUrl());
        binding.player.setTag(video);
        binding.player.setPlayPosition(position);
        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        binding.player.setAutoFullWithSize(false);
        //音频焦点冲突时是否释放
        binding.player.setReleaseWhenLossAudio(false);
        //全屏动画
        binding.player.setShowFullAnimation(true);
        //小屏时不触摸滑动
        binding.player.setIsTouchWiget(false);
        binding.player.setOnProgressListener(video::setPosition);
    }

}
