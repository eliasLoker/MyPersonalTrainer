package com.example.mypersonaltrainer.createprogram.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.createprogram.events.UpdateListEvent
import com.example.mypersonaltrainer.createprogram.interactor.CreateProgramInteractor
import com.example.mypersonaltrainer.data.ExerciseEntity

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateProgramViewModelImpl(private val createProgramInteractor: CreateProgramInteractor) : ViewModel(), CreateProgramViewModel {

    override val programName: ObservableField<String> = ObservableField("")

    override val timeOfRest: ObservableField<String> = ObservableField("")

    override val updateListEvent: SingleLiveEvent<UpdateListEvent> = SingleLiveEvent()

    private lateinit var list: MutableList<ExerciseEntity>

    init {
        val disposable = createProgramInteractor.getAll()
            .subscribe { t: List<ExerciseEntity>? ->
                list = t!!.toMutableList()
                if (list.isNotEmpty()) updateListEvent.postValue(UpdateListEvent(list))
            }
    }
}