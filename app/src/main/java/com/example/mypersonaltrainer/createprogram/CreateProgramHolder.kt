package com.example.mypersonaltrainer.createprogram

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonaltrainer.data.ExerciseEntity

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateProgramHolder(var exerciseListForProgramItemBinding: com.example.mypersonaltrainer.databinding.ExerciseListForProgramItemBinding) :
    RecyclerView.ViewHolder(exerciseListForProgramItemBinding.root), ItemTouchHelperViewHolder {

    fun bind(exerciseEntity: ExerciseEntity) {
        exerciseListForProgramItemBinding.exercise = exerciseEntity
        exerciseListForProgramItemBinding.executePendingBindings()
    }

    override fun onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY)
    }

    override fun onItemClear() {
        itemView.setBackgroundColor(0)
    }
}