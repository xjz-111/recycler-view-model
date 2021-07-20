package com.leslie.recylerviewmodel.test.carousel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.test.BaseActivity;
import com.leslie.recylerviewmodel.test.Utils;
import com.leslie.recylerviewmodel.test.grid.GridViewModel;

import java.util.List;

/**
 * 带轮播图列表
 *
 * 作者：xjzhao
 * 时间：2021-07-19 23:47
 */
public class TestActivity11 extends BaseActivity {
    @Override
    protected void initViewModels(@NonNull List<BaseCommonViewModel> vms) {
        // Header
        vms.add(new CarouselHeaderVm());

        // Grid
        vms.add(new GridViewModel(Utils.getList3()));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 2);
    }
}
