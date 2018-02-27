package com.example.richard.rnoteauth.helpers

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.example.richard.rnoteauth.R

abstract class AbstractSwipeCallback(val context: Context, val swipeDirection: Int) : ItemTouchHelper.SimpleCallback(0, swipeDirection) {

    private val background = ColorDrawable()
    

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
       //Does not allow the note to move
        return false
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val icon: Drawable = getIcon()
        val (intrinsicWidth, intrinsicHeight) = getSize(icon)
        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top
        //The background is being drawed
        background.color = getColor()
        setBounds(itemView, dX)

        background.draw(c)

        val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
        val deleteIconMargin = (itemHeight - intrinsicHeight) / 2

        val (deleteIconLeft, deleteIconRight) = getRelativePositions(itemView, deleteIconMargin, intrinsicWidth)
        val deleteIconBottom = deleteIconTop + intrinsicHeight

        //The icon is being drawed
        icon.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
        icon.draw(c)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun setBounds(itemView: View, dX: Float): Unit = if (isRightSwipe()) {
        background.setBounds(itemView.left + dX.toInt(), itemView.top, itemView.left, itemView.bottom)
    } else {
        background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
    }

    private fun getRelativePositions(itemView: View, deleteIconMargin: Int, intrinsicWidth: Int): Pair<Int, Int> =
        if (isRightSwipe()) {
            Pair(itemView.left + deleteIconMargin, itemView.left + deleteIconMargin + intrinsicWidth)
        } else {
            Pair(itemView.right - deleteIconMargin - intrinsicWidth, itemView.right - deleteIconMargin)
        }

    ///TODO: IMPORTANT INSTANTIATE ITEMS ONLY ONCE ON THE CNSTRUCTOR
    //por defecto devuelve el color de borrar
    private fun getColor(): Int = if (isRightSwipe()) Color.parseColor("#087f23") else Color.parseColor("#f44336")

    private fun getSize(icon: Drawable): Pair<Int, Int> = Pair(icon.intrinsicHeight, icon.intrinsicWidth)

    //Por defecto devuelve el icono de borrar
    private fun getIcon(): Drawable = if (isRightSwipe()) {
        ContextCompat.getDrawable(context, R.drawable.ic_archive_white_32dp)!!
    } else {
        ContextCompat.getDrawable(context, R.drawable.ic_delete_sweep_white_32dp)!!
    }

    private fun isRightSwipe() = swipeDirection == ItemTouchHelper.RIGHT

}
