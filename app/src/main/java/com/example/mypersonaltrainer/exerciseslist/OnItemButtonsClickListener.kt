package com.example.mypersonaltrainer.exerciseslist

import com.example.mypersonaltrainer.data.exercise.ExerciseEntity

/**
 * Created by Alexandr Mikhalev on 12.06.2019.
 *
 * @author Alexandr Mikhalev
 */
interface OnItemButtonsClickListener {

    fun onSettingsClicked(exerciseEntity: ExerciseEntity)

    fun onBasketClicked(exerciseEntity: ExerciseEntity)
}