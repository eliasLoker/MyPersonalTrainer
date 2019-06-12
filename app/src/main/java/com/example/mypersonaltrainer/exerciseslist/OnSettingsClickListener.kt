package com.example.mypersonaltrainer.exerciseslist

import com.example.mypersonaltrainer.data.ExerciseEntity

/**
 * Created by Alexandr Mikhalev on 12.06.2019.
 *
 * @author Alexandr Mikhalev
 */
interface OnSettingsClickListener {

    fun onSettingsClicked(exerciseEntity: ExerciseEntity)
}