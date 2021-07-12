package com.leslie.recylerviewmodel.test.grid;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.test.BaseActivity;
import com.leslie.recylerviewmodel.test.Utils;
import com.leslie.recylerviewmodel.test.headerfoooter.FooterViewModel;

import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-09 12:40
 */
public class TestActivity4 extends BaseActivity {

    @Override
    protected void initViewModels(@NonNull List<BaseCommonViewModel> vms) {
        // Header
        vms.add(new FooterViewModel("###### 这是Header ######"));

        // Grid
        vms.add(new GridViewModel(Utils.getList3()));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 2);
    }
}
