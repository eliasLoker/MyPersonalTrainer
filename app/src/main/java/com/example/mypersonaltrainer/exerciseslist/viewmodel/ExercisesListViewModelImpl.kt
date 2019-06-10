package com.example.mypersonaltrainer.exerciseslist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.exerciseslist.interactor.ExercisesListInteractor

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ExercisesListViewModelImpl(private val exercisesListInteractor: ExercisesListInteractor) : ViewModel(), ExercisesListViewModel {
}