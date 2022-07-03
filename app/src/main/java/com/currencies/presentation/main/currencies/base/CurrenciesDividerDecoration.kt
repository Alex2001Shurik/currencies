package com.currencies.presentation.main.currencies.base

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.currencies.R

class CurrenciesDividerDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val divider = requireNotNull(ContextCompat.getDrawable(context, R.drawable.divider))
    private val dividerPadding = context.resources.getDimensionPixelSize(R.dimen.dp16)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) = with(outRect) {
        top = dividerPadding
        left = dividerPadding
        right = dividerPadding
    }

    override fun onDraw(
        canvas: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.children.forEach { child ->
            divider.setBounds(
                dividerPadding,
                child.bottom - divider.intrinsicHeight,
                parent.width - dividerPadding,
                child.bottom
            )
            divider.draw(canvas)
        }
    }
}
