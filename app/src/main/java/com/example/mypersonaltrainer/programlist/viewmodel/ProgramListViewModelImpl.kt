package com.example.mypersonaltrainer.programlist.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.data.program.ProgramEntity
import com.example.mypersonaltrainer.programlist.events.FragmentEvent
import com.example.mypersonaltrainer.programlist.events.ShowDeleteDialogEvent
import com.example.mypersonaltrainer.programlist.events.ShowEditProgramDialogEvent
import com.example.mypersonaltrainer.programlist.events.UpdateListEvent
import com.example.mypersonaltrainer.programlist.interactor.ProgramListInteractor

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ProgramListViewModelImpl(private val programListInteractor: ProgramListInteractor) : ViewModel(), ProgramListViewModel {

    override val stateProgressBar: ObservableField<Boolean> = ObservableField(true)
    override val stateRecycler: ObservableField<Boolean> = ObservableField(false)
    override val stateEmptyTextView: ObservableField<Boolean> = ObservableField(false)
    override val numberOfPrograms: ObservableField<String> = ObservableField()

    override val showEditProgramDialogEvent: SingleLiveEvent<ShowEditProgramDialogEvent> = SingleLiveEvent()

    override val updateListEvent: SingleLiveEvent<UpdateListEvent> = SingleLiveEvent()

    override val goToTrainingEvent: SingleLiveEvent<FragmentEvent> = SingleLiveEvent()

    override val showDeleteDialogEvent: SingleLiveEvent<ShowDeleteDialogEvent> = SingleLiveEvent()

    private lateinit var list: MutableList<ProgramEntity>

    init {
        val disposable = programListInteractor.getAll()
            .subscribe { t: List<ProgramEntity> ->
                    list = t.toMutableList()
                if (list.isEmpty()) {
                    stateEmptyTextView.set(true)
                    stateProgressBar.set(false)
                } else {
                    numberOfPrograms.set(list.size.toString())
                    updateListEvent.postValue(UpdateListEvent(list))
                    stateRecycler.set(true)
                    stateProgressBar.set(false)
                }
        }
    }

    override fun onClickStartButtonCallback(id: Long) {
        goToTrainingEvent.postValue(FragmentEvent(id))
    }

    override fun onSettingsClickedCallback(id: Long) {
        val disposable = programListInteractor.getProgramById(id).subscribe { t: ProgramEntity ->
            showEditProgramDialogEvent.postValue(ShowEditProgramDialogEvent(t.name, t.timeOfRest.toString()))
        }
    }

    override fun onBasketClickedCallback(id: Long) {
        val disposable = programListInteractor.getProgramById(id).subscribe { t: ProgramEntity ->
            showDeleteDialogEvent.postValue(ShowDeleteDialogEvent(t.name))
        }
    }
}