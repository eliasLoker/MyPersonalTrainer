package com.example.mypersonaltrainer.exerciseslist.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.data.ExerciseEntity
import com.example.mypersonaltrainer.exerciseslist.events.UpdateListEvent
import com.example.mypersonaltrainer.exerciseslist.interactor.ExercisesListInteractor

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ExercisesListViewModelImpl(private val exercisesListInteractor: ExercisesListInteractor) : ViewModel(),
    ExercisesListViewModel {

    override val stateProgressBar: ObservableField<Boolean> = ObservableField(true)
    override val stateRecycler: ObservableField<Boolean> = ObservableField(false)
    override val stateEmptyTextView: ObservableField<Boolean> = ObservableField(false)

    override val updateListEvent: SingleLiveEvent<UpdateListEvent> = SingleLiveEvent()

    private lateinit var list: MutableList<ExerciseEntity>

    init {
        val disposable = exercisesListInteractor.getAll()
            .subscribe { t: List<ExerciseEntity>? ->
                list = t!!.toMutableList()
                if (list.isEmpty()) {
                    stateEmptyTextView.set(true)
                    stateProgressBar.set(false)
                } else {
                    updateListEvent.postValue(UpdateListEvent(list))
                    stateRecycler.set(true)
                    stateProgressBar.set(false)
                }
            }
    }
}