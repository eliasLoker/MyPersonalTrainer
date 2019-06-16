package com.example.mypersonaltrainer.training.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.training.interactor.TrainingInteractor

/**
 * Created by Alexandr Mikhalev on 16.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class TrainingViewModelImpl(private val trainingInteractor: TrainingInteractor): ViewModel(), TrainingViewModel {
}