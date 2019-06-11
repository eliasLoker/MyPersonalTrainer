package com.example.mypersonaltrainer.programlist.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.programlist.interactor.ProgramListInteractor

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ProgramListViewModelImpl(private val programListInteractor: ProgramListInteractor) : ViewModel(), ProgramListViewModel {

    override val stateProgressBar: ObservableField<Boolean> = ObservableField(true)
    override val stateRecycler: ObservableField<Boolean> = ObservableField(false)
    override val stateEmptyTextView: ObservableField<Boolean> = ObservableField(true) //false
}