package com.example.mypersonaltrainer.programlist

import androidx.recyclerview.widget.DiffUtil
import com.example.mypersonaltrainer.data.program.ProgramEntity

/**
 * Created by Alexandr Mikhalev on 14.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class DiffUtilProgramList(private val oldList: List<ProgramEntity>, private val newList: List<ProgramEntity>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
    return oldList.size
    }

    override fun getNewListSize(): Int {
    return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        var isSame = true
        for (i in 0 until newList.size) {
            if (!((oldItem.name == newItem.name) && (oldItem.timeOfRest == newItem.timeOfRest))) isSame =
                false
        }
        return isSame
    }
}