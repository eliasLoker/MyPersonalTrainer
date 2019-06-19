package com.example.mypersonaltrainer.training.interactor

import com.example.mypersonaltrainer.data.exercise.ExerciseDao
import com.example.mypersonaltrainer.data.exercise.ExerciseEntity
import com.example.mypersonaltrainer.data.program.ProgramDao
import com.example.mypersonaltrainer.data.program.ProgramEntity
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Alexandr Mikhalev on 16.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class TrainingInteractor(private val exerciseDao: ExerciseDao, private val programDao: ProgramDao) {

    private lateinit var timer: Observable<Long>

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

    fun getTimer(time: Long): Observable<Long> {
        timer = Observable.interval(1, TimeUnit.SECONDS)
            .map { time - it }
            .takeUntil { aLong -> aLong == 0L }
        return timer
    }
}