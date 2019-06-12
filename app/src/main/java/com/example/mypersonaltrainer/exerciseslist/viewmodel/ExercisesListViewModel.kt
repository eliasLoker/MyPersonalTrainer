package com.example.mypersonaltrainer.exerciseslist.viewmodel

import androidx.databinding.ObservableField
import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.data.ExerciseEntity
import com.example.mypersonaltrainer.exerciseslist.events.EditExerciseDialogEvent
import com.example.mypersonaltrainer.exerciseslist.events.UpdateListEvent

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
interface ExercisesListViewModel {

    val stateProgressBar: ObservableField<Boolean>

    val stateRecycler: ObservableField<Boolean>

    val stateEmptyTextView: ObservableField<Boolean>

    val updateListEvent: SingleLiveEvent<UpdateListEvent>

    val showEditDialogEvent: SingleLiveEvent<EditExerciseDialogEvent>

    fun onSettingsClickedCallback(exerciseEntity: ExerciseEntity)

    fun onButtonSavedClickedCallback(
        title: String,
        numberOfRepeat: String,
        numberOfRepetitions: String,
        timeOfRest: String
    )
}