package com.example.mypersonaltrainer.exerciseslist.interactor

import com.example.mypersonaltrainer.data.exercise.ExerciseDao
import com.example.mypersonaltrainer.data.exercise.ExerciseEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ExercisesListInteractor(private val exerciseDao: ExerciseDao) {

    fun getAll(): Single<List<ExerciseEntity>> {
        return exerciseDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getById(id: Long): Single<ExerciseEntity> {
        return exerciseDao.getById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun update(exerciseEntity: ExerciseEntity): Completable {
        return exerciseDao.update(exerciseEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(exerciseEntity: ExerciseEntity): Completable {
        return exerciseDao.delete(exerciseEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}