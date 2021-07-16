package com.leslie.recylerviewmodel.test.otherVm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.OtherHeaderVmBinding;
import com.leslie.recylerviewmodel.test.BaseActivity;
import com.leslie.recylerviewmodel.test.Utils;
import com.leslie.recylerviewmodel.test.list.ListViewModel;

import java.util.List;

/**
 * 异常和空数据
 *
 * 作者：xjzhao
 * 时间：2021-07-16 18:11
 */
public class TestActivity8 extends BaseActivity implements View.OnClickListener, OnChangeListener {
    private OtherHeaderVmBinding binding;
    private DataType type = DataType.NORMAL;
    private ListViewModel mainVm;
    private EmptyViewModel emptyVm;
    private ErrViewModel errVm;

    @Override
    protected void addView1(@NonNull RelativeLayout parent) {
        super.addView1(parent);
        View hover = LayoutInflater.from(this).inflate(R.layout.other_header_vm, null);
        parent.addView(hover, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        binding = DataBindingUtil.bind(hover);
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
        binding.btn3.setOnClickListener(this);
    }

    @Override
    protected void addRecyclerView(@NonNull RelativeLayout parent) {
        super.addRecyclerView(parent);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
        lp.topMargin = 200;
        lp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        lp.height = RelativeLayout.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected void initViewModels(@NonNull List<BaseCommonViewModel> vms) {
        vms.add(mainVm = new ListViewModel(Utils.getList1()));
        vms.add(emptyVm = new EmptyViewModel());
        vms.add(errVm = new ErrViewModel(this));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btn1){
            onDisplay();
        } else if (v == binding.btn2){
            onEmpty();
        } else if (v == binding.btn3){
            onErr();
        }
    }


    @Override
    public void onDisplay() {
        if (type != DataType.NORMAL){
            mainVm.setList(Utils.getList1());
            emptyVm.cleanList();
            errVm.cleanList();
            type = DataType.NORMAL;
        }
    }

    @Override
    public void onEmpty() {
        if (type != DataType.EMPTY){
            emptyVm.addItem("页面空数据了");
            mainVm.cleanList();
            errVm.cleanList();
            type = DataType.EMPTY;
        }
    }

    @Override
    public void onErr() {
        if (type != DataType.ERR){
            errVm.addItem("页面数据异常了，点击重新获取");
            mainVm.cleanList();
            emptyVm.cleanList();
            type = DataType.ERR;
        }
    }
}
