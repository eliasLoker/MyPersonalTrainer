package com.example.mypersonaltrainer.training.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.data.exercise.ExerciseEntity
import com.example.mypersonaltrainer.data.program.ProgramEntity
import com.example.mypersonaltrainer.training.events.TrainingOffEvent
import com.example.mypersonaltrainer.training.events.UpdateListEvent
import com.example.mypersonaltrainer.training.interactor.TrainingInteractor
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Alexandr Mikhalev on 16.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class TrainingViewModelImpl(private val trainingInteractor: TrainingInteractor, private val programId: Long) :
    ViewModel(), TrainingViewModel {

    private val strForTraining = "Сделай повторения и нажми на кнопку отдыха:"
    private val strForRest = "Жди когда таймер закончится"

    override val programName: ObservableField<String> = ObservableField()
    override val currentExerciseName: ObservableField<String> = ObservableField()
    override val instruction: ObservableField<String> = ObservableField(strForTraining)
    override val counter: ObservableField<String> = ObservableField()

    override val restButtonState: ObservableField<Boolean> = ObservableField(true)

    override val updateListEvent: SingleLiveEvent<UpdateListEvent> = SingleLiveEvent()

    override val trainingOffEvent: SingleLiveEvent<TrainingOffEvent> = SingleLiveEvent()

    private val compositeDisposable = CompositeDisposable()

    private var indexOfCurrentExercise: Int = 0
    private var indexOfCurrentProgram: Int = 0
    private var idListExercises = ArrayList<Long>()
    private var exerciseListForRecycler = ArrayList<Long>()
    private var timeOfRestProgram: Long = 0L
    private var timeOfRestExercise: Long = 0L
    private var numberOfRepetitions: Int = 0

    init {
        val disposableGetById =
            trainingInteractor.getProgramById(programId).subscribe { programEntity: ProgramEntity ->
                programName.set(programEntity.name)
                idListExercises = programEntity.list as ArrayList<Long>
                timeOfRestProgram = programEntity.timeOfRest.toLong()

                val disposableGetEx =
                    trainingInteractor.getExerciseById(idListExercises[indexOfCurrentProgram])
                        .subscribe { exerciseEntity: ExerciseEntity ->
                            initFields(exerciseEntity)
                        }
                compositeDisposable.add(disposableGetEx)
            }
        compositeDisposable.addAll(disposableGetById)
    }

    override fun onClickStartRestButton() {
        if (indexOfCurrentProgram == idListExercises.size - 1 &&
            indexOfCurrentExercise == exerciseListForRecycler.size - 1
        ) {
            trainingOffEvent.postValue(TrainingOffEvent("Тренировка закончилась"))
            return
        }
        if (indexOfCurrentExercise == exerciseListForRecycler.size - 1) {
            startRestBetweenPrograms()
            return
        }
        startRestBetweenRepetitions()
    }

    private fun startRestBetweenRepetitions() {
        trainingInteractor.getTimer(timeOfRestExercise).subscribe(object : Observer<Long> {
            override fun onComplete() {
                //goToNextRepetition()
                indexOfCurrentExercise += 1
                updateListEvent.postValue(UpdateListEvent(exerciseListForRecycler, indexOfCurrentExercise))
                instruction.set(strForTraining)
                counter.set(numberOfRepetitions.toString())
                restButtonState.set(true)
            }

            override fun onSubscribe(d: Disposable) {
                instruction.set(strForRest)
                restButtonState.set(false)
            }

            override fun onNext(t: Long) {
                counter.set(t.toString())
            }

            override fun onError(e: Throwable) {

            }
        })
    }

    private fun startRestBetweenPrograms() {
        trainingInteractor.getTimer(timeOfRestProgram).subscribe(object : Observer<Long> {
            override fun onComplete() {
                instruction.set(strForTraining)
                counter.set(numberOfRepetitions.toString())
                loadNextProgram()
                restButtonState.set(true)
            }

            override fun onSubscribe(d: Disposable) {
                instruction.set(strForRest)
                restButtonState.set(false)
            }

            override fun onNext(t: Long) {
                counter.set(t.toString())
            }

            override fun onError(e: Throwable) {

            }
        })
    }

    private fun loadNextProgram() {
        indexOfCurrentExercise = 0
        indexOfCurrentProgram += 1
        val disposableGetById =
            trainingInteractor.getProgramById(programId).subscribe { programEntity: ProgramEntity ->
                val disposableGetEx =
                    trainingInteractor.getExerciseById(idListExercises[indexOfCurrentProgram])
                        .subscribe { exerciseEntity: ExerciseEntity ->
                            initFields(exerciseEntity)
                        }
                compositeDisposable.add(disposableGetEx)
            }
        compositeDisposable.add(disposableGetById)
    }

    private fun initFields(exerciseEntity: ExerciseEntity) {
        currentExerciseName.set(exerciseEntity.exerciseName)
        numberOfRepetitions = exerciseEntity.numberOfRepetitions
        exerciseListForRecycler.clear()
        for (i in 1..exerciseEntity.numberOfRepeat) {
            exerciseListForRecycler.add(i.toLong())
        }
        counter.set(exerciseEntity.numberOfRepetitions.toString())
        timeOfRestExercise = exerciseEntity.timeOfRest.toLong()
        updateListEvent.postValue(UpdateListEvent(exerciseListForRecycler, indexOfCurrentExercise))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}