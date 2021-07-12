package com.leslie.recylerviewmodel.test.headerfoooter;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;

/**
 * 作者：xjzhao
 * 时间：2021-07-09 11:31
 */
public class FooterViewModel extends BaseCommonViewModel<String> {
    public FooterViewModel(String s) {
        super(s);
    }

    @Override
    public int getVariable(String s, int position) {
        return BR.footer;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.footer_vm;
    }
}
