package com.example.mypersonaltrainer.programlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mypersonaltrainer.programlist.interactor.ProgramListInteractor
import org.jetbrains.annotations.NotNull

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ProgramListFactory(private val programListInteractor: ProgramListInteractor) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(@NotNull modelClass: Class<T>): T {
        return ProgramListViewModelImpl(programListInteractor) as T
    }
}