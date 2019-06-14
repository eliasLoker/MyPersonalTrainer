package com.example.mypersonaltrainer.createprogram

import androidx.recyclerview.widget.DiffUtil
import com.example.mypersonaltrainer.data.exercise.ExerciseEntity

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class DiffUtilCreateProgram(private val oldList: List<ExerciseEntity>, private val newList: List<ExerciseEntity>) :
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
            if (!((oldItem.exerciseName == newItem.exerciseName) && (oldItem.numberOfRepeat == newItem.numberOfRepeat))) isSame =
                false
        }
        return isSame
    }
}