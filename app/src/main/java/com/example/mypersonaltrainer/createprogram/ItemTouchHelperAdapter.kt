package com.example.mypersonaltrainer.createprogram

/**
 * Created by Alexandr Mikhalev on 13.06.2019.
 *
 * @author Alexandr Mikhalev
 */
interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismiss(position: Int)
}