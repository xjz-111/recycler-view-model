package com.leslie.recylerviewmodel.test.video;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.test.BaseActivity;
import com.leslie.recylerviewmodel.test.Utils;
import com.leslie.recylerviewmodel.test.headerfoooter.FooterViewModel;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-16 21:37
 */
public class TestActivity10 extends BaseActivity {
    private VideoViewModel mainVm;

    @Override
    protected void initViewModels(@NonNull List<BaseCommonViewModel> vms) {
        vms.add(new FooterViewModel("###  这是Header  ###"));
        vms.add(mainVm = new VideoViewModel(Utils.getList10()));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }
}
