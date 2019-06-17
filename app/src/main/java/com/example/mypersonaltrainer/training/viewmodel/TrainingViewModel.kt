package com.example.mypersonaltrainer.training.viewmodel

import androidx.databinding.ObservableField

/**
 * Created by Alexandr Mikhalev on 16.06.2019.
 *
 * @author Alexandr Mikhalev
 */
interface TrainingViewModel {

    val programName: ObservableField<String>

    val currentExercise: ObservableField<String>
}