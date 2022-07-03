package com.currencies.presentation.main.currencies.my_currencies.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.currencies.R

abstract class SwipeToDeleteCallback internal constructor(context: Context) :
    ItemTouchHelper.Callback() {

    private val mBackground: ColorDrawable = ColorDrawable().apply {
        color = ContextCompat.getColor(context, R.color.blue_light)
    }

    private val deleteDrawable =
        requireNotNull(ContextCompat.getDrawable(context, R.drawable.ic_remove_20dp))

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0, ItemTouchHelper.LEFT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val isCancelled = dX == 0f && !isCurrentlyActive
        if (isCancelled) {
            clearCanvas(c, itemView, dX)
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        drawSwipeBackground(itemView, dX, c)

        drawDeleteDrawable(itemView, c)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun drawSwipeBackground(itemView: View, dX: Float, c: Canvas) = with(itemView) {
        mBackground.setBounds(right + dX.toInt(), top, right, bottom)
        mBackground.draw(c)
    }

    private fun drawDeleteDrawable(itemView: View, c: Canvas) = with(itemView) {
        val intrinsicHeight = deleteDrawable.intrinsicHeight
        val deleteIconTop = top + (height - intrinsicHeight) / 2
        val deleteIconMargin = (height - intrinsicHeight) / 2
        val deleteIconLeft = right - deleteIconMargin - deleteDrawable.intrinsicWidth
        val deleteIconRight = right - deleteIconMargin
        val deleteIconBottom = deleteIconTop + intrinsicHeight
        deleteDrawable.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
        deleteDrawable.draw(c)
    }

    private fun clearCanvas(c: Canvas, itemView: View, dX: Float) = with(itemView) {
        val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }
        c.drawRect(right + dX, top.toFloat(), right.toFloat(), bottom.toFloat(), clearPaint)
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return SWIPE_THRESHOLD
    }

    private companion object {
        const val SWIPE_THRESHOLD = 0.7F
    }
}
