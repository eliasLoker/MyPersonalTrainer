package com.example.mypersonaltrainer.training.viewmodel

import androidx.databinding.ObservableField
import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.training.events.TrainingOffEvent
import com.example.mypersonaltrainer.training.events.UpdateListEvent

/**
 * Created by Alexandr Mikhalev on 16.06.2019.
 *
 * @author Alexandr Mikhalev
 */
interface TrainingViewModel {

    val programName: ObservableField<String>

    val currentExerciseName: ObservableField<String>

    val instruction: ObservableField<String>

    val counter: ObservableField<String>

    val restButtonState: ObservableField<Boolean>

    val updateListEvent: SingleLiveEvent<UpdateListEvent>

    val trainingOffEvent: SingleLiveEvent<TrainingOffEvent>

    fun onClickStartRestButton()
}