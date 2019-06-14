package com.example.mypersonaltrainer.createprogram.inject

import androidx.lifecycle.ViewModelProviders
import com.example.mypersonaltrainer.createprogram.CreateProgramFragment
import com.example.mypersonaltrainer.createprogram.interactor.CreateProgramInteractor
import com.example.mypersonaltrainer.createprogram.viewmodel.CreateProgramFactory
import com.example.mypersonaltrainer.createprogram.viewmodel.CreateProgramViewModel
import com.example.mypersonaltrainer.createprogram.viewmodel.CreateProgramViewModelImpl
import com.example.mypersonaltrainer.data.exercise.ExerciseDao
import com.example.mypersonaltrainer.data.program.ProgramDao
import dagger.Module
import dagger.Provides

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
class CreateProgramModule {

    @CreateProgramScope
    @Provides
    fun provideCreateProgramInteractor(exerciseDao: ExerciseDao, programDao: ProgramDao) = CreateProgramInteractor(exerciseDao, programDao)

    @CreateProgramScope
    @Provides
    fun provideCreateProgramFactory(createProgramInteractor: CreateProgramInteractor) =
        CreateProgramFactory(createProgramInteractor)

    @CreateProgramScope
    @Provides
    fun provideCreateProgramViewModel(
        createProgramFragment: CreateProgramFragment,
        createProgramFactory: CreateProgramFactory
    ): CreateProgramViewModel {
        return ViewModelProviders.of(createProgramFragment, createProgramFactory)
            .get(CreateProgramViewModelImpl::class.java)
    }
}