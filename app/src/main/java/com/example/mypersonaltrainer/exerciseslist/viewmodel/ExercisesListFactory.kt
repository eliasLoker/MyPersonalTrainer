package com.example.mypersonaltrainer.exerciseslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mypersonaltrainer.exerciseslist.interactor.ExercisesListInteractor
import org.jetbrains.annotations.NotNull

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ExercisesListFactory(private val exercisesListInteractor: ExercisesListInteractor) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(@NotNull modelClass: Class<T>): T {
        return ExercisesListViewModelImpl(exercisesListInteractor) as T
    }
}