package com.example.mypersonaltrainer.createexercise.inject

import androidx.lifecycle.ViewModelProviders
import com.example.mypersonaltrainer.createexercise.CreateExerciseFragment
import com.example.mypersonaltrainer.createexercise.interactor.CreateExerciseInteractor
import com.example.mypersonaltrainer.createexercise.viewmodel.CreateExerciseFactory
import com.example.mypersonaltrainer.createexercise.viewmodel.CreateExerciseViewModel
import com.example.mypersonaltrainer.createexercise.viewmodel.CreateExerciseViewModelImpl
import com.example.mypersonaltrainer.data.exercise.ExerciseDao
import dagger.Module
import dagger.Provides

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
class CreateExerciseModule {

    @CreateExerciseScope
    @Provides
    fun provideCreateExerciseInteractor(exerciseDao: ExerciseDao) = CreateExerciseInteractor(exerciseDao)

    @CreateExerciseScope
    @Provides
    fun provideCreateExerciseFactory(exerciseInteractor: CreateExerciseInteractor) =
        CreateExerciseFactory(exerciseInteractor)

    @CreateExerciseScope
    @Provides
    fun provideCreateExerciseViewModel(
        createExerciseFragment: CreateExerciseFragment,
        createExerciseFactory: CreateExerciseFactory
    ): CreateExerciseViewModel {
        return ViewModelProviders.of(createExerciseFragment, createExerciseFactory)
            .get(CreateExerciseViewModelImpl::class.java)
    }
}