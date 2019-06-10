package com.example.mypersonaltrainer.createexercise.viewmodel

import androidx.databinding.ObservableField
import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.createexercise.events.InputErrorEvent

/**
 * Created by Alexandr Mikhalev on 09.06.2019.
 *
 * @author Alexandr Mikhalev
 */
interface CreateExerciseViewModel {

    val exerciseName: ObservableField<String>

    val numberOfRepeat: ObservableField<String>

    val numberOfRepetitions: ObservableField<String>

    val timeOfRest: ObservableField<String>

    val errorInputEvent: SingleLiveEvent<InputErrorEvent>

    fun onClickCreateExerciseButton()
}