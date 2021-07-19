package com.leslie.recylerviewmodel.test.carousel;

import com.leslie.carousel.BaseCarouselViewModel;
import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.Vm1Binding;
import com.leslie.recylerviewmodel.test.data.ImgData;

import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-19 11:07
 */
public class TestVm1 extends BaseCarouselViewModel<ImgData, Vm1Binding> {

    public TestVm1(List<ImgData> list) {
        super(list);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.carousel_vm1;
    }


    @Override
    public int getVariable(ImgData item, int position, final int positionInViewModel) {
        return BR.vm1;
    }

}
