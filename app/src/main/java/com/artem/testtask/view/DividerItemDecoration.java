package com.artem.testtask.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.artem.testtask.R;


public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    public static final int DEFAULT = R.color.appColor;

    public DividerItemDecoration(Context context, int color) {
        mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        mDivider.setColorFilter(context.getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);
    }

    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}