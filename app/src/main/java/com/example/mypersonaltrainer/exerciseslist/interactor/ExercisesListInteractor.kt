package com.example.mypersonaltrainer.exerciseslist.interactor

import com.example.mypersonaltrainer.data.ExerciseDao
import com.example.mypersonaltrainer.data.ExerciseEntity
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
}