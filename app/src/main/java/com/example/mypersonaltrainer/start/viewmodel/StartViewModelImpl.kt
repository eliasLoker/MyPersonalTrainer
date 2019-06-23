package com.example.mypersonaltrainer.start.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.start.events.FragmentEvent
import com.example.mypersonaltrainer.start.events.ShowSnackbarEvent

/**
 * Created by Alexandr Mikhalev on 09.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class StartViewModelImpl : ViewModel(), StartViewModel {

    override var goToCreateExerciseEvent: SingleLiveEvent<FragmentEvent> = SingleLiveEvent()
    override var goToMyTrainingsEvent: SingleLiveEvent<FragmentEvent> = SingleLiveEvent()

    override var showSnackbarEvent: SingleLiveEvent<ShowSnackbarEvent> = SingleLiveEvent()

    override fun onClickCreateExerciseButton() {
        goToCreateExerciseEvent.postValue(FragmentEvent())
    }

    override fun onClickMyTrainingsButton() {
        goToMyTrainingsEvent.postValue(FragmentEvent())
    }

    override fun onClickQuestionButton() {
        showSnackbarEvent.postValue(ShowSnackbarEvent())
    }
}