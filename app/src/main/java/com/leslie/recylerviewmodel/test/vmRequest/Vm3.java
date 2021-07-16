package com.leslie.recylerviewmodel.test.vmRequest;

import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.Vm3Binding;
import com.leslie.recylerviewmodel.test.Utils;

/**
 * 作者：xjzhao
 * 时间：2021-07-16 19:07
 */
public class Vm3 extends BaseVm<String, Vm3Binding> {

    @Override
    public int getVariable(String s, int position) {
        return BR.vm3;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.vm_3;
    }

    @Override
    public int getSpanCount() {
        return 1;
    }

    @Override
    protected void request() {
        super.request();
        handler.postDelayed(() -> {
            addList(Utils.getList1().subList(0, 3));
        }, 2000);
    }
}
