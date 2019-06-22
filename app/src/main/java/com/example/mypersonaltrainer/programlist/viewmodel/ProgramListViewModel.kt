package com.example.mypersonaltrainer.programlist.viewmodel

import androidx.databinding.ObservableField
import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.programlist.events.FragmentEvent
import com.example.mypersonaltrainer.programlist.events.ShowEditProgramDialogEvent
import com.example.mypersonaltrainer.programlist.events.UpdateListEvent

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
interface ProgramListViewModel {

    val stateProgressBar: ObservableField<Boolean>

    val stateRecycler: ObservableField<Boolean>

    val stateEmptyTextView: ObservableField<Boolean>

    val numberOfPrograms: ObservableField<String>

    val updateListEvent: SingleLiveEvent<UpdateListEvent>

    val goToTrainingEvent: SingleLiveEvent<FragmentEvent>

    val showEditProgramDialogEvent: SingleLiveEvent<ShowEditProgramDialogEvent>

    fun onClickStartButtonCallback(id: Long)

    fun onSettingsClickedCallback(id: Long)
}