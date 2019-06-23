package com.example.mypersonaltrainer.start.viewmodel

import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.start.events.FragmentEvent
import com.example.mypersonaltrainer.start.events.ShowSnackbarEvent

/**
 * Created by Alexandr Mikhalev on 09.06.2019.
 *
 * @author Alexandr Mikhalev
 */
interface StartViewModel {

    var goToCreateExerciseEvent: SingleLiveEvent<FragmentEvent>

    var goToMyTrainingsEvent: SingleLiveEvent<FragmentEvent>

    var showSnackbarEvent: SingleLiveEvent<ShowSnackbarEvent>

    fun onClickCreateExerciseButton()

    fun onClickMyTrainingsButton()

    fun onClickQuestionButton()
}