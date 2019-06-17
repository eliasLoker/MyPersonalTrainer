package com.example.mypersonaltrainer.training.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mypersonaltrainer.training.interactor.TrainingInteractor
import org.jetbrains.annotations.NotNull

/**
 * Created by Alexandr Mikhalev on 16.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class TrainingFactory(private val trainingInteractor: TrainingInteractor,  private val programId: Long) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(@NotNull modelClass: Class<T>): T {
        return TrainingViewModelImpl(trainingInteractor, programId) as T
    }
}