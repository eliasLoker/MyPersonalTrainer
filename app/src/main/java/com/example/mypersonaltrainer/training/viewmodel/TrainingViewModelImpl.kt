package com.example.mypersonaltrainer.training.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.data.exercise.ExerciseEntity
import com.example.mypersonaltrainer.data.program.ProgramEntity
import com.example.mypersonaltrainer.training.interactor.TrainingInteractor

/**
 * Created by Alexandr Mikhalev on 16.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class TrainingViewModelImpl(private val trainingInteractor: TrainingInteractor, private val programId: Long) :
    ViewModel(), TrainingViewModel {

    override val programName: ObservableField<String> = ObservableField()
    override val currentExercise: ObservableField<String> = ObservableField()

    private lateinit var programEntity: ProgramEntity

    init {
        val disposableGetById =
            trainingInteractor.getProgramById(programId).subscribe { t1: ProgramEntity ->
                programEntity = t1
                programName.set(t1.name)

                programEntity.list[0]

                val disposableGetEx =
                    trainingInteractor.getExerciseById(programEntity.list[0]).subscribe { t1: ExerciseEntity ->
                        currentExercise.set(t1.exerciseName)
                    }
            }
    }
}