package com.example.mypersonaltrainer.createexercise.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.createexercise.interactor.CreateExerciseInteractor

/**
 * Created by Alexandr Mikhalev on 09.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateExerciseViewModelImpl(private val createExerciseInteractor: CreateExerciseInteractor): ViewModel(), CreateExerciseViewModel {
}