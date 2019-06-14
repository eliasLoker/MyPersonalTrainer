package com.example.mypersonaltrainer.createprogram.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.createprogram.events.DialogEvent
import com.example.mypersonaltrainer.createprogram.events.UpdateListEvent
import com.example.mypersonaltrainer.createprogram.interactor.CreateProgramInteractor
import com.example.mypersonaltrainer.data.ExerciseEntity
import java.util.*

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateProgramViewModelImpl(private val createProgramInteractor: CreateProgramInteractor) : ViewModel(),
    CreateProgramViewModel {

    override val programName: ObservableField<String> = ObservableField("")

    override val timeOfRest: ObservableField<String> = ObservableField("")

    override val updateListEvent: SingleLiveEvent<UpdateListEvent> = SingleLiveEvent()

    override val showDialogEvent: SingleLiveEvent<DialogEvent> = SingleLiveEvent()

    private lateinit var list: MutableList<ExerciseEntity>

    val TAG = "CreateProgramVM"

    init {
        val disposable = createProgramInteractor.getAll()
            .subscribe { t: List<ExerciseEntity>? ->
                list = t!!.toMutableList()
                if (list.isNotEmpty()) updateListEvent.postValue(UpdateListEvent(list))
            }
    }

    override fun onItemMoveCallback(fromPosition: Int, toPosition: Int) {
        Collections.swap(list, fromPosition, toPosition)
        updateListEvent.postValue(UpdateListEvent(list))
        Log.d(TAG, "onItemMoveCallback")
        Log.d(TAG, list[0].exerciseName)
        Log.d(TAG, list[1].exerciseName)
    }

    override fun onItemDismissCallback(position: Int) {
        if (list.size == 1) {
            Log.d(TAG, "onItemDismissCallback return")
            return
        }
        Log.d(TAG, "onItemDismissCallback do not return")
        list.removeAt(position)
        updateListEvent.postValue(UpdateListEvent(list))
        Log.d(TAG, list.size.toString())
    }

    override fun onClickSaveButtonCallback(programName: String, timeOfRest: String) {

    }

    override fun onClickCreateProgram() {
        showDialogEvent.postValue(DialogEvent())
    }
}