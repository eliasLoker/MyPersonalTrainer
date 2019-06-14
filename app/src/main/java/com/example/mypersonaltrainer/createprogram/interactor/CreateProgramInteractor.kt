package com.example.mypersonaltrainer.createprogram.interactor

import com.example.mypersonaltrainer.data.exercise.ExerciseDao
import com.example.mypersonaltrainer.data.exercise.ExerciseEntity
import com.example.mypersonaltrainer.data.program.ProgramDao
import com.example.mypersonaltrainer.data.program.ProgramEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateProgramInteractor(private val exerciseDao: ExerciseDao, private val programDao: ProgramDao) {

    fun getAll(): Single<List<ExerciseEntity>> {
        return exerciseDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertProgram(programEntity: ProgramEntity): Completable {
        return programDao.insert(programEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}