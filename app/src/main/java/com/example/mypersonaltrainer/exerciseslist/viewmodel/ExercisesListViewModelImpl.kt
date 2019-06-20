package com.example.mypersonaltrainer.exerciseslist.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.data.exercise.ExerciseEntity
import com.example.mypersonaltrainer.exerciseslist.events.DeleteEvent
import com.example.mypersonaltrainer.exerciseslist.events.EditExerciseDialogEvent
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
    override val numberOfExercises: ObservableField<String> = ObservableField()

    override val updateListEvent: SingleLiveEvent<UpdateListEvent> = SingleLiveEvent()

    override val showEditDialogEvent: SingleLiveEvent<EditExerciseDialogEvent> = SingleLiveEvent()

    override val showDeleteDialogEvent: SingleLiveEvent<DeleteEvent> = SingleLiveEvent()

    private lateinit var exerciseEntityClicked: ExerciseEntity

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
                    numberOfExercises.set(list.size.toString())
                }
            }
    }

    override fun onSettingsClickedCallback(exerciseEntity: ExerciseEntity) {
        exerciseEntityClicked = exerciseEntity
        val longId: Long = exerciseEntity.id!!
        val disposable = exercisesListInteractor.getById(longId)
            .subscribe { t: ExerciseEntity? ->
                showEditDialogEvent.postValue(EditExerciseDialogEvent(t!!))
            }
    }

    override fun onBasketClickedCallback(exerciseEntity: ExerciseEntity) {
        exerciseEntityClicked = exerciseEntity
        showDeleteDialogEvent.postValue(DeleteEvent(exerciseEntityClicked.exerciseName))
    }

    override fun onButtonSavedClickedCallback(
        title: String,
        numberOfRepeat: String,
        numberOfRepetitions: String,
        timeOfRest: String
    ) {
        exerciseEntityClicked.exerciseName = title
        exerciseEntityClicked.numberOfRepeat = numberOfRepeat.toInt()
        exerciseEntityClicked.numberOfRepetitions = numberOfRepetitions.toInt()
        exerciseEntityClicked.timeOfRest = timeOfRest.toInt()
        val disposableUpdate = exercisesListInteractor.update(exerciseEntityClicked).subscribe {
            val disposableGetAll = exercisesListInteractor.getAll().subscribe { t: List<ExerciseEntity>? ->
                list = t!!.toMutableList()
                updateListEvent.postValue(UpdateListEvent(list))
            }
        }
    }

    override fun onConfirmDeleteDialogClickedCallback() {
        val disposableDelete = exercisesListInteractor.delete(exerciseEntityClicked).subscribe {
            val disposableGetAll = exercisesListInteractor.getAll().subscribe { t: List<ExerciseEntity>? ->
                list = t!!.toMutableList()
                if (list.isEmpty()) {
                    stateRecycler.set(false)
                    stateEmptyTextView.set(true)
                } else {
                    stateRecycler.set(true)
                    stateEmptyTextView.set(false)
                    updateListEvent.postValue(UpdateListEvent(list))
                }

            }
        }
    }
}