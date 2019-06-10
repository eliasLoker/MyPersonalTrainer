package com.example.mypersonaltrainer.createexercise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mypersonaltrainer.createexercise.interactor.CreateExerciseInteractor
import org.jetbrains.annotations.NotNull

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateExerciseFactory(private val createExerciseInteractor: CreateExerciseInteractor) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(@NotNull modelClass: Class<T>): T {
        return CreateExerciseViewModelImpl(createExerciseInteractor) as T
    }
}