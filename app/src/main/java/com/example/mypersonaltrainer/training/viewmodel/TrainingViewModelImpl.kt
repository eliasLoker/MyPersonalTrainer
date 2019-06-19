package com.example.mypersonaltrainer.training.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.data.exercise.ExerciseEntity
import com.example.mypersonaltrainer.data.program.ProgramEntity
import com.example.mypersonaltrainer.training.events.TrainingOffEvent
import com.example.mypersonaltrainer.training.events.UpdateListEvent
import com.example.mypersonaltrainer.training.interactor.TrainingInteractor
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by Alexandr Mikhalev on 16.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class TrainingViewModelImpl(private val trainingInteractor: TrainingInteractor, private val programId: Long) :
    ViewModel(), TrainingViewModel {

    override val programName: ObservableField<String> = ObservableField()
    override val currentExerciseName: ObservableField<String> = ObservableField()
    override val instruction: ObservableField<String> = ObservableField("Сделай повторения и нажми на кнопку отдыха")
    override val counter: ObservableField<String> = ObservableField()

    override val updateListEvent: SingleLiveEvent<UpdateListEvent> = SingleLiveEvent()

    override val trainingOffEvent: SingleLiveEvent<TrainingOffEvent> = SingleLiveEvent()

    private var indexOfCurrentExercise: Int = 0
    private var indexOfCurrentProgram: Int = 0
    private var indexListExercises = ArrayList<Long>()
    private var exerciseListForRecycler = ArrayList<Long>()
    private var timeOfRestProgram: Long = 0L
    private var timeOfRestExercise: Long = 0L

    init {
        val disposableGetById =
            trainingInteractor.getProgramById(programId).subscribe { programEntity: ProgramEntity ->
                programName.set(programEntity.name)
                indexListExercises = programEntity.list as ArrayList<Long>
                timeOfRestProgram = programEntity.timeOfRest.toLong()

                val disposableGetEx =
                    trainingInteractor.getExerciseById(indexListExercises[indexOfCurrentProgram])
                        .subscribe { exerciseEntity: ExerciseEntity ->
                            currentExerciseName.set(exerciseEntity.exerciseName)
                            //indexOfCurrentProgram++
                            for (i in 1..exerciseEntity.numberOfRepetitions) {
                                exerciseListForRecycler.add(i.toLong())
                            }
                            counter.set(exerciseEntity.numberOfRepetitions.toString())
                            timeOfRestExercise = exerciseEntity.timeOfRest.toLong()
                            updateListEvent.postValue(UpdateListEvent(exerciseListForRecycler, indexOfCurrentExercise))
                        }
            }
    }

    override fun onClickStartRestButton() {
        trainingInteractor.getTimer(timeOfRestExercise).subscribe(object : Observer<Long> {
            override fun onComplete() {
                goToNextRepetition()
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Long) {
                counter.set(t.toString())
            }

            override fun onError(e: Throwable) {

            }
        })
    }

    private fun goToNextRepetition() {
        indexOfCurrentExercise += 1
        if (indexOfCurrentProgram == indexListExercises.size - 1 && indexOfCurrentExercise == exerciseListForRecycler.size) {
            trainingOffEvent.postValue(TrainingOffEvent("Тренировка закончилась"))
            Log.d("TVM", "IF2")
            return
        }

        if (indexOfCurrentExercise == exerciseListForRecycler.size) {
            Log.d("TVM", "IF1")
            loadProgram()
            return
        }

        updateListEvent.postValue(UpdateListEvent(exerciseListForRecycler, indexOfCurrentExercise))
    }

    private fun loadProgram() {
        indexOfCurrentExercise = 0
        indexOfCurrentProgram += 1
        val disposableGetById =
            trainingInteractor.getProgramById(programId).subscribe { programEntity: ProgramEntity ->
                val disposableGetEx =
                    trainingInteractor.getExerciseById(indexListExercises[indexOfCurrentProgram])
                        .subscribe { exerciseEntity: ExerciseEntity ->
                            currentExerciseName.set(exerciseEntity.exerciseName)
                            exerciseListForRecycler.clear()
                            for (i in 1..exerciseEntity.numberOfRepetitions) {
                                exerciseListForRecycler.add(i.toLong())
                            }
                            counter.set(exerciseEntity.numberOfRepetitions.toString())
                            timeOfRestExercise = exerciseEntity.timeOfRest.toLong()
                            updateListEvent.postValue(UpdateListEvent(exerciseListForRecycler, indexOfCurrentExercise))
                        }
            }
    }
}