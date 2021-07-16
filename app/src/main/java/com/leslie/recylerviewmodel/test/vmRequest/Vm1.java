package com.leslie.recylerviewmodel.test.vmRequest;

import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.Vm1Binding;
import com.leslie.recylerviewmodel.test.Utils;
import com.leslie.recylerviewmodel.test.data.ImgData;

/**
 * 作者：xjzhao
 * 时间：2021-07-16 19:07
 */
public class Vm1 extends BaseVm<ImgData, Vm1Binding> {
    @Override
    public int getVariable(ImgData imgData, int position) {
        return BR.vm1;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.vm_1;
    }

    @Override
    public int getSpanCount() {
        return 1;
    }

    @Override
    protected void request() {
        super.request();
        handler.postDelayed(() -> {
            addList(Utils.getList2());
        }, 2000);
    }
}
