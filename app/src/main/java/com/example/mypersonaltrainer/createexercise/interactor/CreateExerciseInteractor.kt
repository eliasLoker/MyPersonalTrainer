package com.example.mypersonaltrainer.createexercise.interactor

import com.example.mypersonaltrainer.data.ExerciseDao
import com.example.mypersonaltrainer.data.ExerciseEntity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateExerciseInteractor(private val exerciseDao: ExerciseDao) {
        fun createExercise(exerciseEntity: ExerciseEntity): Completable {
            return exerciseDao.insert(exerciseEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
}