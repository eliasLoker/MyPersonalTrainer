package com.example.mypersonaltrainer.training.inject

import androidx.lifecycle.ViewModelProviders
import com.example.mypersonaltrainer.data.exercise.ExerciseDao
import com.example.mypersonaltrainer.data.program.ProgramDao
import com.example.mypersonaltrainer.training.TrainingFragment
import com.example.mypersonaltrainer.training.interactor.TrainingInteractor
import com.example.mypersonaltrainer.training.viewmodel.TrainingFactory
import com.example.mypersonaltrainer.training.viewmodel.TrainingViewModel
import com.example.mypersonaltrainer.training.viewmodel.TrainingViewModelImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alexandr Mikhalev on 16.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
class TrainingModule {

    @Provides
    @TrainingScope
    fun provideTrainingInteractor(exerciseDao: ExerciseDao, programDao: ProgramDao) =
        TrainingInteractor(exerciseDao, programDao)

    @Provides
    @TrainingScope
    fun provideTrainingFactory(trainingInteractor: TrainingInteractor) = TrainingFactory(trainingInteractor)

    @Provides
    @TrainingScope
    fun provideTraining(trainingFragment: TrainingFragment, trainingFactory: TrainingFactory) : TrainingViewModel {
        return ViewModelProviders.of(trainingFragment, trainingFactory).get(TrainingViewModelImpl::class.java)
    }
}