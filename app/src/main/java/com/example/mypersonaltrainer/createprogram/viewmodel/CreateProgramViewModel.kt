package com.example.mypersonaltrainer.createprogram.viewmodel

import androidx.databinding.ObservableField
import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.createprogram.events.UpdateListEvent

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
interface CreateProgramViewModel {

    val programName: ObservableField<String>

    val timeOfRest: ObservableField<String>

    val updateListEvent: SingleLiveEvent<UpdateListEvent>

    fun onItemMoveCallback(fromPosition: Int, toPosition: Int)

    fun onItemDismissCallback(position: Int)
}