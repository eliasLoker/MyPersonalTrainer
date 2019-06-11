package com.example.mypersonaltrainer.createprogram.viewmodel

import androidx.databinding.ObservableField

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
interface CreateProgramViewModel {

    val programName: ObservableField<String>

    val timeOfRest: ObservableField<String>
}