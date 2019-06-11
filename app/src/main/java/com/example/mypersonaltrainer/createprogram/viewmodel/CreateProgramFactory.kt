package com.example.mypersonaltrainer.createprogram.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mypersonaltrainer.createprogram.interactor.CreateProgramInteractor
import org.jetbrains.annotations.NotNull

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateProgramFactory(private val createProgramInteractor: CreateProgramInteractor) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(@NotNull modelClass: Class<T>): T {
        return CreateProgramViewModelImpl(createProgramInteractor) as T
    }
}