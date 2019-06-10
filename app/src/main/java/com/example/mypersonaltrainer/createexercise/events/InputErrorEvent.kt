package com.example.mypersonaltrainer.createexercise.events

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class InputErrorEvent(val errorType: ErrorType) {
    enum class ErrorType {
        TITLE_ERROR,
        NUMBER_OF_REPEAT_ERROR,
        NUMBER_OF_REPETITIONS_ERROR,
        TIME_OF_REST_ERROR
    }
}