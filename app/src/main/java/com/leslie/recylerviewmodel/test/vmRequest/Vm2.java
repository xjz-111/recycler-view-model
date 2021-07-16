package com.leslie.recylerviewmodel.test.vmRequest;

import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.Vm2Binding;
import com.leslie.recylerviewmodel.test.Utils;
import com.leslie.recylerviewmodel.test.data.ImgData;

/**
 * 作者：xjzhao
 * 时间：2021-07-16 19:07
 */
public class Vm2 extends BaseVm<ImgData, Vm2Binding> {
    @Override
    public int getVariable(ImgData imgData, int position) {
        return BR.vm2;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.vm_2;
    }

    @Override
    public int getSpanCount() {
        return 2;
    }

    @Override
    protected void request() {
        super.request();
        handler.postDelayed(() -> {
            addList(Utils.getList3());
        }, 1000);
    }
}
