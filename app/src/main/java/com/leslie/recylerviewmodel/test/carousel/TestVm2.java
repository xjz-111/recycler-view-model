package com.leslie.recylerviewmodel.test.carousel;

import com.leslie.carousel.BaseCarouselViewModel;
import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.Vm2Binding;
import com.leslie.recylerviewmodel.test.data.ImgData;

import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-19 19:21
 */
public class TestVm2 extends BaseCarouselViewModel<ImgData, Vm2Binding> {

    public TestVm2(List<ImgData> list) {
        super(list);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.carousel_vm2;
    }

    @Override
    public int getVariable(ImgData item, int position, int positionInViewModel) {
        return BR.vm2;
    }

}
