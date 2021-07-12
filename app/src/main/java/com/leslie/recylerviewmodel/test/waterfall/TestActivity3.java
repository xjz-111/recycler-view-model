package com.leslie.recylerviewmodel.test.waterfall;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.test.BaseActivity;
import com.leslie.recylerviewmodel.test.Utils;
import com.leslie.recylerviewmodel.test.headerfoooter.FooterViewModel;

import java.util.List;

/**
 * 瀑布流列表
 *
 * 作者：xjzhao
 * 时间：2021-07-09 11:38
 */
public class TestActivity3 extends BaseActivity {
    @Override
    protected void initViewModels(@NonNull List<BaseCommonViewModel> vms) {
        // Header
        vms.add(new FooterViewModel("###### 这是Header ######"));

        // 瀑布流ViewModel
        vms.add(new WaterfallViewModel(Utils.getList3()));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        final StaggeredGridLayoutManager lm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        lm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        return lm;
    }

}
