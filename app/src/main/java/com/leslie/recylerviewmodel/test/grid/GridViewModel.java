package com.leslie.recylerviewmodel.test.grid;

import androidx.databinding.library.baseAdapters.BR;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.GridVmBinding;
import com.leslie.recylerviewmodel.test.data.ImgData;

import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-09 12:41
 */
public class GridViewModel extends BaseCommonViewModel<ImgData, GridVmBinding> {

    public GridViewModel(List<ImgData> list) {
        super(list);
    }

    @Override
    public int getVariable(ImgData imgData, int position) {
        return BR.gridVm;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.grid_vm;
    }

    @Override
    public int getSpanCount() {
        return 2;
    }
}
