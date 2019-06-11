package com.example.mypersonaltrainer.programlist.viewmodel

import androidx.databinding.ObservableField

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
interface ProgramListViewModel {

    val stateProgressBar: ObservableField<Boolean>

    val stateRecycler: ObservableField<Boolean>

    val stateEmptyTextView: ObservableField<Boolean>
}