package com.example.mypersonaltrainer.exerciseslist

import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonaltrainer.data.ExerciseEntity

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ExercisesListHolder(var exerciseListItemBinding: com.example.mypersonaltrainer.databinding.ExerciseListItemBinding) :
    RecyclerView.ViewHolder(exerciseListItemBinding.root) {
    fun bind(exerciseEntity: ExerciseEntity) {
        exerciseListItemBinding.exercise = exerciseEntity
        exerciseListItemBinding.executePendingBindings()
    }
}