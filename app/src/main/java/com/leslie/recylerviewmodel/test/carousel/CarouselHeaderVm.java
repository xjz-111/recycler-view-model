package com.leslie.recylerviewmodel.test.carousel;

import androidx.annotation.NonNull;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.CarouselHeaderVmBinding;
import com.leslie.recylerviewmodel.test.Utils;
import com.leslie.recylerviewmodel.test.data.ImgData;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-19 23:52
 */
public class CarouselHeaderVm extends BaseCommonViewModel<String, CarouselHeaderVmBinding> {
    public CarouselHeaderVm() {
        super("");
    }

    @Override
    public int getVariable(String s, int position) {
        return BR.chv;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.carousel_header_vm;
    }

    @Override
    protected void initView(@NonNull CarouselHeaderVmBinding binding, @NonNull String s, int position, List<Object> payloads) {
        super.initView(binding, s, position, payloads);
        TestVm1 vm1 = new TestVm1(Utils.getList11_1());
        TestVm2 vm2 = new TestVm2(Utils.getList11_2());
        binding.cv.setViewModel(vm1, vm2).notifyDataSetChanged();
        final List<ImgData> titles = new ArrayList<>();
        titles.addAll(vm1.getList());
        titles.addAll(vm2.getList());
        binding.cv.addOnPageChangeListener((vm, position1) -> {
            binding.text.setText(titles.get(position1).getTitle());
        });
    }
}
