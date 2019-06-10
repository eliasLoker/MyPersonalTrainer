package com.example.mypersonaltrainer.createexercise.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mypersonaltrainer.SingleLiveEvent
import com.example.mypersonaltrainer.createexercise.events.FragmentEvent
import com.example.mypersonaltrainer.createexercise.events.InputErrorEvent
import com.example.mypersonaltrainer.createexercise.interactor.CreateExerciseInteractor
import com.example.mypersonaltrainer.data.ExerciseEntity

/**
 * Created by Alexandr Mikhalev on 09.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateExerciseViewModelImpl(private val createExerciseInteractor: CreateExerciseInteractor): ViewModel(), CreateExerciseViewModel {

    override val exerciseName: ObservableField<String> = ObservableField("")
    override val numberOfRepeat: ObservableField<String> = ObservableField("")
    override val numberOfRepetitions: ObservableField<String> = ObservableField("")
    override val timeOfRest: ObservableField<String> = ObservableField("")

    override val errorInputEvent: SingleLiveEvent<InputErrorEvent> = SingleLiveEvent()
    override val goToExerciseListEvent: SingleLiveEvent<FragmentEvent> = SingleLiveEvent()

    override fun onClickCreateExerciseButton() {
        if (exerciseName.get().equals("") || exerciseName.get()!!.length < 3) {
            errorInputEvent.postValue(InputErrorEvent(InputErrorEvent.ErrorType.TITLE_ERROR))
            return
        }
        if (numberOfRepeat.get().equals("")) {
            errorInputEvent.postValue(InputErrorEvent(InputErrorEvent.ErrorType.NUMBER_OF_REPEAT_ERROR))
            return
        }
        if (numberOfRepetitions.get().equals("")) {
            errorInputEvent.postValue(InputErrorEvent(InputErrorEvent.ErrorType.NUMBER_OF_REPETITIONS_ERROR))
            return
        }
        if (timeOfRest.get().equals("")) {
            errorInputEvent.postValue(InputErrorEvent(InputErrorEvent.ErrorType.TIME_OF_REST_ERROR))
            return
        }
        val exerciseEntity = ExerciseEntity(exerciseName.get()!!, numberOfRepeat.get()!!.toInt(),
            numberOfRepetitions.get()!!.toInt(), timeOfRest.get()!!.toInt())
        val disposable = createExerciseInteractor.createExercise(exerciseEntity).subscribe{goToExerciseListEvent.postValue(FragmentEvent())}
        /*
        when {
            exerciseName.get().equals(null) || exerciseName.get()!!.length < 3
            -> errorInputEvent.postValue(InputErrorEvent(InputErrorEvent.ErrorType.TITLE_ERROR))
            numberOfRepeat.get().equals(null)
                    -> errorInputEvent.postValue(InputErrorEvent(InputErrorEvent.ErrorType.NUMBER_OF_REPEAT_ERROR))
            numberOfRepetitions.get().equals(null)
                    -> errorInputEvent.postValue(InputErrorEvent(InputErrorEvent.ErrorType.NUMBER_OF_REPETITIONS_ERROR))
            timeOfRest.get().equals(null)
                    -> errorInputEvent.postValue(InputErrorEvent(InputErrorEvent.ErrorType.TIME_OF_REST_ERROR))
        }
        */
    }
}