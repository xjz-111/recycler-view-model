package com.leslie.recylerviewmodel.test.otherVm;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;

/**
 * 作者：xjzhao
 * 时间：2021-07-16 18:30
 */
public class EmptyViewModel extends BaseCommonViewModel<String> {
    @Override
    public int getVariable(String s, int position) {
        return BR.empty;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.empty_vm;
    }
}
