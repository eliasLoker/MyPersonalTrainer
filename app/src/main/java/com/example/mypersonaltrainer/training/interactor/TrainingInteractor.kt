package com.example.mypersonaltrainer.training.interactor

import com.example.mypersonaltrainer.data.exercise.ExerciseDao
import com.example.mypersonaltrainer.data.exercise.ExerciseEntity
import com.example.mypersonaltrainer.data.program.ProgramDao
import com.example.mypersonaltrainer.data.program.ProgramEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alexandr Mikhalev on 16.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class TrainingInteractor(private val exerciseDao: ExerciseDao, private val programDao: ProgramDao) {

    fun getProgramById(id: Long): Single<ProgramEntity> {
        return programDao.getById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getExerciseById(id: Long): Single<ExerciseEntity> {
        return exerciseDao.getById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}