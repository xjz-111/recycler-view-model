package com.leslie.recylerviewmodel.test.otherVm;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.BR;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.ErrVmBinding;

import java.util.List;

/**
 * 作者：xjzhao
 * 时间：2021-07-16 18:36
 */
public class ErrViewModel extends BaseCommonViewModel<String> {
    private OnChangeListener onChangeListener;

    public ErrViewModel(OnChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    @Override
    public int getVariable(String s, int position) {
        return BR.err;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.err_vm;
    }

    @Override
    protected void initView(@NonNull ViewDataBinding binding, @NonNull String s, int position, List<Object> payloads) {
        super.initView(binding, s, position, payloads);
        ErrVmBinding mBinding = (ErrVmBinding) binding;
        mBinding.btn1.setOnClickListener(v -> {
            if (null != onChangeListener){
                onChangeListener.onDisplay();
            }
        });
    }
}
