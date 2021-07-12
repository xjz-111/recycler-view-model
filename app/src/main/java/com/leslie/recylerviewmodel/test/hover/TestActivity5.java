package com.leslie.recylerviewmodel.test.hover;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.test.BaseActivity;
import com.leslie.recylerviewmodel.test.Utils;
import com.leslie.recylerviewmodel.test.headerfoooter.HeaderViewModel;

import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-09 18:42
 */
public class TestActivity5 extends BaseActivity {


    @Override
    protected void initViewModels(@NonNull List<BaseCommonViewModel> vms) {
        // Header
        vms.add(new HeaderViewModel(Utils.getList2()));

        // Item
        vms.add(new HoverViewModel(Utils.getList3()));

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 2);
    }
}
