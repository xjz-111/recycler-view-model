package com.leslie.recylerviewmodel.test.hover;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.leslie.recylerviewmodel.test.Utils;

/**
 * 作者：xjzhao
 * 时间：2021-07-09 22:25
 */
public class HoverItemDecoration extends RecyclerView.ItemDecoration {

    private HoverViewModel viewModel;
    private Paint paint = new Paint();
    private Paint textPaint = new Paint();
    private int childCount;
    private Object tag;
    // 悬停条距离顶部的高度
    private int scrollTop = -1;


    public HoverItemDecoration(HoverViewModel viewModel) {
        this.viewModel = viewModel;

        paint.setColor(Color.GREEN);

        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(8);
        textPaint.setTextSize(50);
        textPaint.setTextAlign(Paint.Align.CENTER);



    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private int getRestMeasureWidth(@NonNull View view) {
        return view.getMeasuredWidth() - view.getPaddingEnd() - view.getPaddingStart();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private int getRestMeasureHeight(@NonNull View view) {
        return view.getMeasuredHeight() - view.getPaddingTop() - view.getPaddingBottom();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        // item上方
        childCount = parent.getLayoutManager().getChildCount();

        // 该类型的第一个顶部加要悬停的内容
        tag = viewModel.getItem(0);

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            if (null != child) {
                if (tag == child.getTag()) {
                    scrollTop = child.getTop() - 200;
                    c.drawRect(child.getLeft(), child.getTop() - 200, Utils.screenWidth, child.getTop(), paint);
                    drawText(c, new RectF(child.getLeft(), child.getTop() - 200,Utils.screenWidth, child.getTop()));
                    break;
                }
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        // 顶部的悬浮条
        if (scrollTop <= 0) {
            c.drawRect(0, 0, Utils.screenWidth, Math.min(scrollTop, 200) <= 0 ? 200 : Math.min(scrollTop, 200), paint);
            drawText(c, new RectF(0, 0, Utils.screenWidth, Math.min(scrollTop, 200) <= 0 ? 200 : Math.min(scrollTop, 200)));
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // 该类型的第一行顶部加要悬停的内容
        tag = view.getTag();
        boolean isTop = false;
        for (int i = 0; i < viewModel.getSpanCount(); i++) {
            isTop = isTop || tag == viewModel.getItem(i);
        }
        if (isTop) {
            outRect.set(0, 200, 0, 0);
        }
    }

    private void drawText(Canvas c, RectF rectF) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float baseline = rectF.centerY() + distance;
        c.drawText("这是悬浮条", rectF.centerX(), baseline, textPaint);
    }

}
