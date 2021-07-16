package com.leslie.recylerviewmodel.test.waterfall;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.WaterfallVmBinding;
import com.leslie.recylerviewmodel.test.data.ImgData;

import java.util.List;

/**
 * 瀑布流
 *
 * 作者：xjzhao
 * 时间：2021-07-09 11:51
 */
public class WaterfallViewModel extends BaseCommonViewModel<ImgData, WaterfallVmBinding> {

    public WaterfallViewModel(List<ImgData> list) {
        super(list);
    }

    @Override
    public int getVariable(ImgData imgData, int position) {
        return BR.waterfallImg;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.waterfall_vm;
    }

    @Override
    public int getSpanCount() {
        return 2;
    }
}
