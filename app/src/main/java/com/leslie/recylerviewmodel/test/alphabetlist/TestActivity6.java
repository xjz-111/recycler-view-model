package com.leslie.recylerviewmodel.test.alphabetlist;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recycler_view_model.BaseCommonViewModel;
import com.leslie.recylerviewmodel.R;
import com.leslie.recylerviewmodel.databinding.HoverViewBinding;
import com.leslie.recylerviewmodel.test.BaseActivity;
import com.leslie.recylerviewmodel.test.Utils;
import com.leslie.recylerviewmodel.test.data.Alphabet;

import java.util.List;

/**
 * 分类悬停列表
 *
 * 作者：xjzhao
 * 时间：2021-07-12 16:41
 */
public class TestActivity6 extends BaseActivity implements AlphabetViewModel.OnScrollListener{
    private HoverViewBinding binding;

    @Override
    protected void addView2(@NonNull RelativeLayout parent) {
        View hover = LayoutInflater.from(this).inflate(R.layout.hover_view, null);
        hover.setVisibility(View.VISIBLE);
        parent.addView(hover);
        binding = DataBindingUtil.bind(hover);

    }

    @Override
    protected void initViewModels(@NonNull List<BaseCommonViewModel> vms) {
        vms.add(new AlphabetViewModel(Utils.getList6(), this));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }


    private int lastTopMargin = 0;
    @Override
    public void onScroll(int top, @NonNull Alphabet alphabet) {
        int topMargin = Math.min(210, top) < 210 ? top - 210 : 0;
        if (lastTopMargin != topMargin){
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) binding.parent.getLayoutParams();
            lp.topMargin = topMargin;
            binding.parent.setLayoutParams(lp);
            binding.parentText.setText(alphabet.getParent());
            lastTopMargin = topMargin;
        }
    }
}
