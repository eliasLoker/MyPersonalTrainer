package com.example.mypersonaltrainer.programlist.inject

import androidx.lifecycle.ViewModelProviders
import com.example.mypersonaltrainer.data.exercise.ExerciseDao
import com.example.mypersonaltrainer.programlist.ProgramListFragment
import com.example.mypersonaltrainer.programlist.interactor.ProgramListInteractor
import com.example.mypersonaltrainer.programlist.viewmodel.ProgramListFactory
import com.example.mypersonaltrainer.programlist.viewmodel.ProgramListViewModel
import com.example.mypersonaltrainer.programlist.viewmodel.ProgramListViewModelImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
class ProgramListModule {

    @ProgramListScope
    @Provides
    fun provideProgramListInteractor(exerciseDao: ExerciseDao) = ProgramListInteractor(exerciseDao)

    @ProgramListScope
    @Provides
    fun provideProgramListFactory(programListInteractor: ProgramListInteractor) =
        ProgramListFactory(programListInteractor)

    @ProgramListScope
    @Provides
    fun provideProgramListViewModel(
        programListFragment: ProgramListFragment,
        programListFactory: ProgramListFactory
    ): ProgramListViewModel {
        return ViewModelProviders.of(programListFragment, programListFactory).get(ProgramListViewModelImpl::class.java)
    }
}