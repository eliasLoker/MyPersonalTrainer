package com.example.mypersonaltrainer.createprogram.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.createprogram.interactor.CreateProgramInteractor

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateProgramViewModelImpl(private val createProgramInteractor: CreateProgramInteractor) : ViewModel(), CreateProgramViewModel {

    override val programName: ObservableField<String> = ObservableField("")

    override val timeOfRest: ObservableField<String> = ObservableField("")
}