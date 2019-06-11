package com.example.mypersonaltrainer.createprogram

import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonaltrainer.data.ExerciseEntity

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateProgramHolder(var exerciseListForProgramItemBinding: com.example.mypersonaltrainer.databinding.ExerciseListForProgramItemBinding) :
    RecyclerView.ViewHolder(exerciseListForProgramItemBinding.root) {

    fun bind(exerciseEntity: ExerciseEntity) {
        exerciseListForProgramItemBinding.exercise = exerciseEntity
        exerciseListForProgramItemBinding.executePendingBindings()
    }
}