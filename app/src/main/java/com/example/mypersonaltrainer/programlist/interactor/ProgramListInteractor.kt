package com.example.mypersonaltrainer.programlist.interactor

import com.example.mypersonaltrainer.data.program.ProgramDao
import com.example.mypersonaltrainer.data.program.ProgramEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ProgramListInteractor(private val programDao: ProgramDao) {

    fun getAll(): Single<List<ProgramEntity>> {
        return programDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getProgramById(id: Long): Single<ProgramEntity> {
        return programDao.getById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}