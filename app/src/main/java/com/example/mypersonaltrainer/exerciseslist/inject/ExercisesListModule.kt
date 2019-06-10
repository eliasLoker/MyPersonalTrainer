package com.example.mypersonaltrainer.exerciseslist.inject

import androidx.lifecycle.ViewModelProviders
import com.example.mypersonaltrainer.data.ExerciseDao
import com.example.mypersonaltrainer.exerciseslist.ExercisesListFragment
import com.example.mypersonaltrainer.exerciseslist.interactor.ExercisesListInteractor
import com.example.mypersonaltrainer.exerciseslist.viewmodel.ExercisesListFactory
import com.example.mypersonaltrainer.exerciseslist.viewmodel.ExercisesListViewModel
import com.example.mypersonaltrainer.exerciseslist.viewmodel.ExercisesListViewModelImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
class ExercisesListModule {

    @ExercisesListScope
    @Provides
    fun provideExercisesListInteractor(exerciseDao: ExerciseDao) = ExercisesListInteractor(exerciseDao)

    @ExercisesListScope
    @Provides
    fun provideExercisesListFactory(exercisesListInteractor: ExercisesListInteractor) = ExercisesListFactory(exercisesListInteractor)

    @ExercisesListScope
    @Provides
    fun provideExercisesListViewModel(exercisesListFragment: ExercisesListFragment, exercisesListFactory: ExercisesListFactory) : ExercisesListViewModel {
        return ViewModelProviders.of(exercisesListFragment, exercisesListFactory).get(ExercisesListViewModelImpl::class.java)
    }
}